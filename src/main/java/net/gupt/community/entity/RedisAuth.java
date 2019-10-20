package net.gupt.community.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * <h3>GuptCommunityServer</h3>
 * <p>Redis的配置属性</p>
 *
 * @author : Cui
 * @date : 2019-08-09 10:25
 **/
@Data
@Component
public class RedisAuth {
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private Integer port;
    @Value("${spring.redis.password}")
    private String password;
}
