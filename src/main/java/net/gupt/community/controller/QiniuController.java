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
import org.springframework.web.bind.annotation.RequestParam;
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

    /**
     * 获取七牛Token接口
     *
     * @param accessKey 七牛的访问密钥key <br/>
     * @param secretKey 七牛的密钥 <br/>
     * @param bucket    七牛的bucket<br/>
     * @return 结果集输出信息
     */
    @RequestMapping(value = "/getUpToken", method = RequestMethod.GET)
    public Result getUpToken(@RequestParam(value = "accessKey") String accessKey,
                             @RequestParam(value = "secretKey") String secretKey,
                             @RequestParam(value = "bucket") String bucket) {
        Auth auth = Auth.create(accessKey, secretKey);
        Qiniu upToken = new Qiniu(auth.uploadToken(bucket));
        if (upToken.getUpToken() == null) {
            return Result.error(CodeMsg.FAILED);
        }
        return Result.success(CodeMsg.SUCCESS, upToken);
    }

}
