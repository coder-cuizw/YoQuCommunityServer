package net.gupt.community.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * <h3>GuptCommunityServer</h3>
 * <p>
 * Token配置：
 * 注意：classpath:prop/encrypt.properties文件会在上传github时忽略
 * </p>
 *
 * @author : Cui
 * @date : 2020-06-20 15:01
 **/
@Data
@Component
@PropertySource("classpath:prop/encrypt.properties")
public class AesTokenConfig {

    @Value("${aesToken.key}")
    private String aesTokenKey;
    @Value("${aesToken.iv}")
    private String aesTokenIv;
}
