package net.gupt.community.controller;

import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.model.BatchStatus;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import net.gupt.community.annotation.AuthToken;
import net.gupt.community.entity.CodeMsg;
import net.gupt.community.entity.Qiniu;
import net.gupt.community.entity.Result;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/deleteImg")
    public Result deleteImg(@RequestParam(value = "accessKey") String accessKey,
                            @RequestParam(value = "secretKey") String secretKey,
                            @RequestParam(value = "bucket") String bucket,
                            @RequestParam(value = "img") String[] images) {
        boolean deleteStatus = false;
        Configuration cfg = new Configuration(Region.autoRegion());
        Auth auth = Auth.create(accessKey, secretKey);
        try {
            BucketManager bucketManager = new BucketManager(auth, cfg);
            BucketManager.BatchOperations batchOperations = new BucketManager.BatchOperations();
            batchOperations.addDeleteOp(bucket, images);
            Response response = bucketManager.batch(batchOperations);
            BatchStatus[] batchStatusList = response.jsonToObject(BatchStatus[].class);
            for (int i = 0; i < images.length; i++) {
                BatchStatus status = batchStatusList[i];
                String key = images[i];
                log.info("键{}" + key + "\n");
                deleteStatus = status.code == 200;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return deleteStatus ? Result.success(CodeMsg.DELETE_SUCCESS) : Result.error(CodeMsg.DELETE_FAILED);

    }

}
