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
     * 鉴权失败后返回的HTTP错误码，默认为401
     */
    private static final int UNAUTHORIZED_ERROR_CODE = HttpServletResponse.SC_UNAUTHORIZED;

    /**
     * 存放登录用户模型Key的Request Key
     */
    private static final String REQUEST_CURRENT_OPEN_ID = "OPEN_ID";

    /**
     * redis存储token设置的过期时间，两小时
     */
    private static final Integer TOKEN_EXPIRE_TIME = 60 * 60 * 2 * 1000;
    private static final String BINDING_PATH = "binding";

    private final StudentMapper studentMapper;
    private final RedisAuth redisAuth;

    public AuthorizationInterceptor(StudentMapper studentMapper, RedisAuth redisAuth) {
        this.studentMapper = studentMapper;
        this.redisAuth = redisAuth;
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
            Jedis jedis = new Jedis(redisAuth.getHost(), redisAuth.getPort());
            jedis.auth(redisAuth.getPassword());
            String redisOpenId;
            if (token != null && token.length() != 0) {
                redisOpenId = jedis.get(token);
            } else {
                print(response, Result.error(CodeMsg.TOKEN_NONEMPTY));
                return false;
            }
            String[] tokenParams = new String(AesUtil.decrypt(token), StandardCharsets.UTF_8).split("\\|");
            String openId = tokenParams[0];
            Student student = studentMapper.findStudentByOpenId(openId);

            long tokeExpireTime = Long.parseLong(tokenParams[2]) + TOKEN_EXPIRE_TIME;
            long leftAliveTime = tokeExpireTime - System.currentTimeMillis();
            log.info("令牌可用的截至时间：{}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    .format(new Date(tokeExpireTime)));
            if (student == null & !request.getServletPath().contains(BINDING_PATH)) {
                print(response, Result.error(CodeMsg.BINDING_NOT));
                return false;
            }

            if (leftAliveTime > 0) {
                // NX是不存在时才set， XX是存在时才set， EX是秒，PX是毫秒
                jedis.set(token, openId, "NX", "PX", leftAliveTime);
            } else {
                print(response, Result.error(CodeMsg.TOKEN_EXPIRED));
                return false;
            }

            /*
             * 如果redisOpenId不为空或者去除空格不为空串则设置openId
             */
            if (redisOpenId != null && !redisOpenId.trim().isEmpty()) {
                //设置Student对象
                request.setAttribute("Student", student);
                request.setAttribute(REQUEST_CURRENT_OPEN_ID, redisOpenId);
            } else {
                // NX是不存在时才set， XX是存在时才set， EX是秒，PX是毫秒
                jedis.set(token, openId, "NX", "PX", leftAliveTime);
                log.info("设置过期时间成功！");
                request.setAttribute(REQUEST_CURRENT_OPEN_ID, openId);
            }
            jedis.close();
            return true;
        }
        request.setAttribute(REQUEST_CURRENT_OPEN_ID, null);
        return true;
    }

    private void print(HttpServletResponse response, Result codeMsg) {
        try {
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            PrintWriter out = response.getWriter();
            out.print(new Gson().toJson(codeMsg));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }

}
