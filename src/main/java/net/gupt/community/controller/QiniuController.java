package net.gupt.community.controller;

import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import net.gupt.community.annotation.AuthToken;
import net.gupt.community.entity.CodeMsg;
import net.gupt.community.entity.Qiniu;
import net.gupt.community.entity.Result;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h3>GuptCommunityServer</h3>
 * <p>七牛upToken</p>
 *
 * @author : Cui
 * @date : 2019-08-25 19:00
 **/
@Slf4j
@AuthToken
@RestController
@RequestMapping(value = "/qiniu", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class QiniuController {
    private final Qiniu qiniu;

    public QiniuController(Qiniu qiniu) {
        this.qiniu = qiniu;
    }

    /**
     * 获取七牛Token接口
     *
     * @return 结果集输出信息
     */
    @RequestMapping(value = "/getUpToken", method = RequestMethod.GET)
    public Result getUpToken() {
        Auth auth = Auth.create(qiniu.getAccessKey(), qiniu.getSecretKey());
        Qiniu upToken = new Qiniu(auth.uploadToken(qiniu.getBucket()));
        return upToken.getUpToken() == null ?
                Result.error(CodeMsg.FAILED) : Result.success(CodeMsg.SUCCESS, upToken);
    }

}
