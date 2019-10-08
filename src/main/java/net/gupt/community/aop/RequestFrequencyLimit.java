package net.gupt.community.aop;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import net.gupt.community.annotation.LimitFrequency;
import net.gupt.community.entity.CodeMsg;
import net.gupt.community.entity.RedisAuth;
import net.gupt.community.entity.Result;
import net.gupt.community.entity.Student;
import net.gupt.community.util.AesUtil;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * ClassName  RequestFrequencyLimit <br/>
 * Description 请求频率Aop增强类 <br/>
 *
 * @author YG
 * @version 1.0
 * @date 2019/10/29:46<br/>
 * @since JDK 1.8
 */
@Slf4j
@Component
@Aspect
public class RequestFrequencyLimit {

    private Student student;

    private RedisAuth redisAuth;

    private final Gson gson;


    public RequestFrequencyLimit(Student student, RedisAuth redisAuth, Gson gson) {
        this.student = student;
        this.redisAuth = redisAuth;
        this.gson = gson;
    }

    @Before("within(@org.springframework.web.bind.annotation.RestController *) && @annotation(limit)")
    public void requestLimit(LimitFrequency limit) {
        Jedis jedis = new Jedis(redisAuth.getHost(), redisAuth.getPort());
        jedis.auth(redisAuth.getPassword());
        //切换redis数据库到db1
        jedis.select(1);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
        //拼接key并加密
        student = (Student) request.getAttribute("Student");
        String uid = String.valueOf(student.getUid());
        String servletPath = request.getServletPath().replace("/", "|");
        String key = uid.concat(servletPath);
        log.info("key{}:" + key);
        String encKey;
        try {
            encKey = AesUtil.byteToHexString(AesUtil.encrypt(key));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (!encKey.trim().isEmpty()) {
            //每请求一次redis会自增一
            jedis.incr(encKey);
            //如果redis存在key，设置过期时间，默认为一分钟
            if (jedis.exists(encKey)) {
                jedis.expire(encKey, (int) (limit.time() / 1000));
            }
            boolean checkResult = checkByRedis(limit, encKey, jedis);
            jedis.close();
            if (response != null) {
                //响应给前端
                response.setCharacterEncoding("UTF-8");
                response.setHeader("Content-Type", "application/json;charset=UTF-8");
                OutputStream writer;
                if (!checkResult) {
                    try {
                        writer = response.getOutputStream();
                        writer.write(gson.toJson(Result.error(CodeMsg.REQUEST_FREQUENT)).getBytes(UTF_8));
                        writer.close();
                    } catch (IOException e) {
                        throw new RuntimeException("请求过于频繁，超出限制");
                    }
                    //阻塞请求
                    throw new RuntimeException("请求过于频繁，超出限制");
                }
            }


        }
    }


    /**
     * 从Redis获取访问次数
     *
     * @param limit  限制的时间
     * @param encKey 加密后的key
     * @return boolean
     */
    private boolean checkByRedis(LimitFrequency limit, String encKey, Jedis jedis) {
        Integer incrByCount = Integer.valueOf(jedis.get(encKey));
        //如果redis的计数大于设置的次数，返回false，表示频繁请求
        return incrByCount <= limit.count();
    }

}
