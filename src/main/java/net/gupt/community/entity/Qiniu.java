package net.gupt.community.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * <h3>GuptCommunityServer</h3>
 * <p>七牛</p>
 *
 * @author : Cui
 * @date : 2019-08-26 16:56
 **/

@Data
@Component
@PropertySource("classpath:prop/qiniu.properties")
public class Qiniu {

    String upToken;
    @Value("${qiniu.accessKey}")
    private String accessKey;
    @Value("${qiniu.secretKey}")
    private String secretKey;
    @Value("${qiniu.bucket}")
    private String bucket;

    public Qiniu() {
        super();
    }

    public Qiniu(String upToken) {
        this.upToken = upToken;
    }

    public String getUpToken() {
        return upToken;
    }

    public void setUpToken(String upToken) {
        this.upToken = upToken;
    }

}
