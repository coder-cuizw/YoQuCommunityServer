package net.gupt.community.interceptor;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import net.gupt.community.annotation.AuthToken;
import net.gupt.community.entity.CodeMsg;
import net.gupt.community.entity.RedisAuth;
import net.gupt.community.entity.Result;
import net.gupt.community.entity.Student;
import net.gupt.community.mapper.StudentMapper;
import net.gupt.community.util.AesUtil;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <h3>gupt-community</h3>
 * <p>鉴权拦截</p>
 *
 * @author : Cui
 * @date : 2019-07-31 05:03
 **/
@Slf4j
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    /**
     * 存放鉴权信息的Header名称，默认是Authorization
     */
    private static final String HTTP_HEADER_NAME = "Authorization";

    /**
     * redis存储token设置的过期时间，两小时
     */
    private static final Integer TOKEN_EXPIRE_TIME = 60 * 60 * 2 * 1000;
    private static final String BINDING_PATH = "binding";
    private static final String CHAR_NULL = "null";

    private final StudentMapper studentMapper;

    private Jedis jedis;

    public AuthorizationInterceptor(StudentMapper studentMapper, RedisAuth redisAuth) {
        this.studentMapper = studentMapper;
        jedis = new Jedis(redisAuth.getHost(), redisAuth.getPort());
        jedis.auth(redisAuth.getPassword());
        log.info(redisAuth.getHost());
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if (method.getAnnotation(AuthToken.class) != null ||
                handlerMethod.getBeanType().getAnnotation(AuthToken.class) != null) {
            String token = request.getHeader(HTTP_HEADER_NAME);
            log.info("从请求获取的令牌是 {} ", token);

            String redisOpenId;
            if (token != null && token.length() != 0 && !CHAR_NULL.equals(token)) {
                redisOpenId = jedis.get(token);
            } else {
                return print(response, Result.error(CodeMsg.TOKEN_NONEMPTY));
            }
            String[] tokenParams = new String(AesUtil.decrypt(token), StandardCharsets.UTF_8).split("\\|");
            String openId = tokenParams[0];
            Student student = studentMapper.findStudentByOpenId(openId);

            long tokeExpireTime = Long.parseLong(tokenParams[2]) + TOKEN_EXPIRE_TIME;
            long leftAliveTime = tokeExpireTime - System.currentTimeMillis();
            log.info("令牌可用的截至时间：{}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    .format(new Date(tokeExpireTime)));
            if (student == null & !request.getServletPath().contains(BINDING_PATH)) {
                return print(response, Result.error(CodeMsg.BINDING_NOT));
            }

            if (leftAliveTime > 0 && redisOpenId == null) {
                // NX是不存在时才set， XX是存在时才set， EX是秒，PX是毫秒
                jedis.set(token, openId, "NX", "PX", leftAliveTime);
            } else if (leftAliveTime <= 0) {
                return print(response, Result.error(CodeMsg.TOKEN_EXPIRED));
            }

            request.setAttribute("Student", student);
        }
        return true;
    }

    private boolean print(HttpServletResponse response, Result codeMsg) {
        try {
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            PrintWriter out = response.getWriter();
            out.print(new Gson().toJson(codeMsg));
            jedis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }

}
