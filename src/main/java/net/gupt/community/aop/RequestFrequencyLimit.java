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

    private RedisAuth redisAuth;
    private final Gson gson;


    public RequestFrequencyLimit(RedisAuth redisAuth, Gson gson) {
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
        Student student = (Student) request.getAttribute("Student");
        String uid = String.valueOf(student.getUid());
        String servletPath = request.getServletPath().replace("/", "|");
        //拼接Key
        String key = uid.concat(servletPath);
        String encKey = encyKey(key);
        boolean result = incrKey(encKey, jedis, limit);
        if (result) {
            responseResult(response);
        }
    }

    /**
     * 向redis插入数据方法
     *
     * @param key   <br/>
     * @param jedis <br/>
     * @param limit <br/>
     * @return boolean
     */
    private boolean incrKey(String key, Jedis jedis, LimitFrequency limit) {
        boolean state = true;
        if (!key.trim().isEmpty() && jedis != null && limit != null) {
            jedis.incr(key);
            if (Integer.parseInt(jedis.get(key)) < limit.count()) {
                jedis.expire(key, (int) (limit.time() / 1000));
            }
            state = !checkByRedis(limit, key, jedis);
            jedis.close();
        }
        return state;
    }

    /**
     * 从Redis获取访问次数方法
     *
     * @param limit  限制的时间
     * @param encKey 加密后的key
     * @return boolean
     */
    private boolean checkByRedis(LimitFrequency limit, String encKey, Jedis jedis) {
        Integer incrByCount = Integer.valueOf(jedis.get(encKey));
        //如果redis的计数大于设置的次数，如果返回false，表示频繁请求
        return incrByCount <= limit.count();
    }

    /**
     * 向前端响应方法
     *
     * @param response <br/>
     */
    private void responseResult(HttpServletResponse response) {
        if (response != null) {
            response.setHeader("Content-Type", "application/json;charset=UTF-8");
            try {
                OutputStream writer = response.getOutputStream();
                writer.write(gson.toJson(Result.error(CodeMsg.REQUEST_FREQUENT)).getBytes(UTF_8));
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //阻塞请求
            throw new RuntimeException("请求过于频繁，超出限制");
        }
    }

    /**
     * 加密函数
     *
     * @param key <br/>
     * @return String
     */
    private String encyKey(String key) {
        try {
            return AesUtil.byteToHexString(AesUtil.encrypt(key));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
