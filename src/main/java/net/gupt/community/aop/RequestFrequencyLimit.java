package net.gupt.community.aop;

import lombok.extern.slf4j.Slf4j;
import net.gupt.community.annotation.LimitFrequency;
import net.gupt.community.entity.RedisAuth;
import net.gupt.community.entity.Student;
import net.gupt.community.exception.IllegalRequestException;
import net.gupt.community.util.AesUtil;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;

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

    private final HttpServletRequest request;
    private final RedisAuth redisAuth;

    public RequestFrequencyLimit(RedisAuth redisAuth, HttpServletRequest request) {
        this.request = request;
        this.redisAuth = redisAuth;
    }

    @Before("within(@org.springframework.web.bind.annotation.RestController *)&& @annotation(limit)")
    public void requestLimit(LimitFrequency limit) {
        Jedis jedis = new Jedis(redisAuth.getHost(), redisAuth.getPort());
        jedis.auth(redisAuth.getPassword());
        //切换redis数据库到db1
        jedis.select(1);
        Student student = (Student) request.getAttribute("Student");
        String uid = String.valueOf(student.getUid());
        String servletPath = request.getServletPath().replace("/", "|");
        //拼接Key
        String key = uid.concat(servletPath);
        //将key进行Aes加密
        String encKey = encyKey(key);
        boolean result = incrKey(encKey, jedis, limit);
        //当结果返回true向页面输出
        if (result) {
            //抛出非法请求异常
            throw new IllegalRequestException("请求频繁");
        }
    }

    /**
     * 向redis插入数据方法
     *
     * @param key   加密的键
     * @param jedis redis操作对象
     * @param limit <br/>
     * @return boolean
     */
    private boolean incrKey(String key, Jedis jedis, LimitFrequency limit) {
        boolean state = false;
        if (!key.trim().isEmpty() && jedis != null && limit != null) {
            jedis.incr(key);
            //如果获取到Redis的次数小于设置的次数，设置过期时间
            if (Integer.parseInt(jedis.get(key)) < limit.count()) {
                jedis.expire(key, (int) (limit.time() / 1000));
            }
            //请求频繁返回false
            state = !checkByRedis(limit, key, jedis);
            jedis.close();
        }
        return state;
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
        return incrByCount <= limit.count();
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
