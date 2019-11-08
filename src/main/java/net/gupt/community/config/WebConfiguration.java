package net.gupt.community.config;

import net.gupt.community.interceptor.AuthorizationInterceptor;
import net.gupt.community.mapper.StudentMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <h3>gupt-community</h3>
 * <p>Web配置器</p>
 *
 * @author : Cui
 * @date : 2019-07-31 05:01
 **/
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private final StudentMapper studentMapper;

    public WebConfiguration(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    /**
     * 添加拦截器
     *
     * @param registry 注册拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthorizationInterceptor(studentMapper))
                .addPathPatterns("/**");
    }

}
