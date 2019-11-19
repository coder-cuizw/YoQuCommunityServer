package net.gupt.community.aop;

import lombok.extern.slf4j.Slf4j;
import net.gupt.community.annotation.VisitorLimit;
import net.gupt.community.entity.Student;
import net.gupt.community.exception.IllegalRequestException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * ClassName  GuestAccessLimit <br/>
 * Description 游客登录逻辑 <br/>
 *
 * @author YG
 * @version 1.0
 * @date 2019/11/19 17:07<br/>
 * @since JDK 1.8
 */
@Slf4j
@Component
@Aspect
@Service
public class GuestAccessLimit {
    private final HttpServletRequest request;

    public GuestAccessLimit(HttpServletRequest request) {
        this.request = request;
    }

    @Before("within(@org.springframework.stereotype.Service *) && @annotation(visitorLimit)")
    public void guestAccessLimit(VisitorLimit visitorLimit) {
        final int visitorUid = 1818181818;
        final String visitorOpenId = "visitor";
        final String visitorUnionId = "visitor";
        Student student = (Student) request.getAttribute("Student");
        int uid = student.getUid();
        String openId = student.getOpenId();
        String unionId = student.getUnionId();
        if (uid == visitorUid && openId.equals(visitorOpenId) && unionId.equals(visitorUnionId)) {
            // 抛出非法请求异常
            throw new IllegalRequestException("游客无权限访问");
        }
    }
}
