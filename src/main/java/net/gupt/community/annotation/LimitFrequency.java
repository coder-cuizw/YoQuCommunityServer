package net.gupt.community.annotation;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName  LimitFrequency <br/>
 * Description 限制请求频率注解 <br/>
 *
 * @author YG
 * @version 1.0
 * @date 2019/10/29:37<br/>
 * @since JDK 1.8
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface LimitFrequency {
    /**
     * 允许访问的次数，默认10次
     */
    int count() default 10;

    /**
     * 时间段，单位毫秒，默认一分钟
     */
    long time() default 60000;
}
