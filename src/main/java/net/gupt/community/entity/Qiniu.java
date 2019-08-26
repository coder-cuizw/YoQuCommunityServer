package net.gupt.community.entity;

/**
 * <h3>GuptCommunityServer</h3>
 * <p>七牛</p>
 *
 * @author : Cui
 * @date : 2019-08-26 16:56
 **/
public class Qiniu {

    String upToken;

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
