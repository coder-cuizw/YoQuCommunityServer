package net.gupt.community.interceptor;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import net.gupt.community.annotation.AuthToken;
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

    private final StudentMapper studentMapper;

    public AuthorizationInterceptor(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
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

            log.info("从请求获取令牌是 {} ", token);
            log.info("当前系统时间是：{}", System.currentTimeMillis());
            Jedis jedis = new Jedis("119.3.181.96", 6379);
            jedis.auth("guptcommunity");
            String redisOpenId = "";
            if (token != null && token.length() != 0) {
                redisOpenId = jedis.get(token);
                log.info("从Redis获取用户名为 {}", redisOpenId);
            } else {
                print(response, 401, "token不能为空");
                return false;
            }

            String[] tokenParams = new String(AesUtil.decrypt(token), StandardCharsets.UTF_8).split("\\|");
            String openId = tokenParams[0];
            long tokeExpireTime = Long.parseLong(tokenParams[2]) + TOKEN_EXPIRE_TIME;
            long leftAliveTime = tokeExpireTime - System.currentTimeMillis();
            log.info("令牌可存活时间：{} 秒", leftAliveTime / 1000);
            log.info("令牌可用的截至时间：{}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    .format(new Date(tokeExpireTime)));

            Student student = studentMapper.findStudentByOpenId(openId);
            System.out.println();
            if (student == null & !request.getServletPath().contains("binding")) {
                print(response, 500, "该用户未绑定邮院社区，请先绑定");
                return false;
            }

            if (leftAliveTime > 0) {
                // NX是不存在时才set， XX是存在时才set， EX是秒，PX是毫秒
                jedis.set(token, openId, "XX", "PX", leftAliveTime);
            } else {
                log.info("Token已过期");
                print(response, 400, "token已过期");
                return false;
            }

            if (redisOpenId != null && !redisOpenId.trim().isEmpty()) {
                jedis.close();
                request.setAttribute(REQUEST_CURRENT_OPEN_ID, redisOpenId);
                return true;
            } else {
                // NX是不存在时才set， XX是存在时才set， EX是秒，PX是毫秒
                jedis.set(token, openId, "NX", "PX", leftAliveTime);
                log.info("设置过期时间成功！");
                jedis.close();
                request.setAttribute(REQUEST_CURRENT_OPEN_ID, redisOpenId);
                return true;
            }
        }
        request.setAttribute(REQUEST_CURRENT_OPEN_ID, null);
        return true;
    }

    private void print(HttpServletResponse response,int code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        try {
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            PrintWriter out = response.getWriter();
            out.print(new Gson().toJson(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
