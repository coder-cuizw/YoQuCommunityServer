package net.gupt.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * ClassName  WebSocketConfiguration <br/>
 * Description WebSocket配置类 <br/>
 *
 * @author YG
 * @version 1.0
 * @date 2019/12/5 14:44<br/>
 * @since JDK 1.8
 */
@Configuration
public class WebSocketConfiguration {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
