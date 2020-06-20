package net.gupt.community.annotation;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName  VisitorLimit <br/>
 * Description 游客访问限制注解 <br/>
 *
 * @author YG
 * @version 1.0
 * @date 2019/11/19 17:00<br/>
 * @since JDK 1.8
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface VisitorLimit {
}
