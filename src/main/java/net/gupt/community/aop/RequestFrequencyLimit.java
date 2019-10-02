package net.gupt.community.aop;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import net.gupt.community.annotation.LimitFrequency;
import net.gupt.community.entity.CodeMsg;
import net.gupt.community.entity.RedisAuth;
import net.gupt.community.entity.Result;
import net.gupt.community.entity.Student;
import net.gupt.community.util.AesUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

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
    @Autowired
    private Student student;
    @Autowired
    private RedisAuth redisAuth;
    @Autowired
    Gson gson;


    @Before("within(@org.springframework.web.bind.annotation.RestController *) && @annotation(limit)")
    public void requestLimit(JoinPoint joinPoint, LimitFrequency limit) {
        Object[] args = joinPoint.getArgs();
        log.info("参数" + args);
        Jedis jedis = new Jedis(redisAuth.getHost(), redisAuth.getPort());
        jedis.auth(redisAuth.getPassword());
        /**
         * 选择db1数据库
         */
        jedis.select(1);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
        /**
         * 得到Student对象并获取值组装key
         */
        student = (Student) request.getAttribute("Student");
        String uid = String.valueOf(student.getUid());
        String ip = request.getRemoteAddr();
        String servletPath = request.getServletPath();
        String key = ip.concat("|").concat(uid).concat("|").concat(servletPath);
        String encKey;
        /**
         * 使用AESUtils进行加密
         */
        try {
            encKey = AesUtil.byteToHexString(AesUtil.encrypt(key));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (!encKey.trim().isEmpty() && encKey != null) {
            jedis.incrBy(encKey, 1);
            /**
             * 如果存在这个key,设置过期时间,默认为一分钟
             */
            if (jedis.exists(encKey)) {
                jedis.expire(encKey, (int) (limit.time() / 1000));
            }
            /**
             * 判断并向页面输出
             */
            Boolean checkResult = checkByRedis(limit, encKey, jedis);
            /**
             * 关闭连接
             */
            if (jedis != null) {
                jedis.close();
            }
            /**
             * 响应给前端
             */
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Type", "application/json;charset=UTF-8");
            response.setHeader("noMore-content-type", "exception/requestToMore");
            OutputStream writer = null;
            if (!checkResult) {
                try {
                    writer = response.getOutputStream();
                    writer.write(gson.toJson(Result.error(CodeMsg.REQUEST_MORE)).getBytes("UTF-8"));
                } catch (IOException e) {
                    throw new RuntimeException("请求过于频繁，超出限制");
                } finally {
                    try {
                        writer.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                /**
                 * 中断操作
                 */
                throw new RuntimeException("请求过于频繁，超出限制");
            }
        }
    }


    /**
     * 从Redis获取访问次数
     *
     * @param limit
     * @param encKey 加密后的key
     * @return
     */
    private boolean checkByRedis(LimitFrequency limit, String encKey, Jedis jedis) {
        Integer incrByCount = Integer.valueOf(jedis.get(encKey));
        //如果获取的次数大于设置的次数
        if (incrByCount > limit.count()) {
            return false;
        } else {
            return true;
        }
    }

}
