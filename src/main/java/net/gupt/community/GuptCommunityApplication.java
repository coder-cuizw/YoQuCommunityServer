package net.gupt.community;

import net.gupt.community.controller.websocket.WebSocketMsgController;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * SpringBoot默认启动类
 *
 * @author Cui
 */
@SpringBootApplication
@MapperScan(basePackages = "net.gupt.community.mapper")
public class GuptCommunityApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext application = SpringApplication.run(GuptCommunityApplication.class, args);
        WebSocketMsgController.setApplicationContext(application);
    }

    /**
     * 跨域：
     *      测试环境默认不过滤
     *
     * @return CorsConfiguration
     */
    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowCredentials(true);
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }
}
