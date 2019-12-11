package net.gupt.community.util;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.model.BatchStatus;
import com.qiniu.storage.model.FetchRet;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import net.gupt.community.entity.Img;

import java.util.List;

/**
 * ClassName  QiniuUtil <br/>
 * Description 七牛工具类 <br/>
 *
 * @author YG
 * @version 1.0
 * @date 2019/10/1318:23<br/>
 * @since JDK 1.8
 */

@Slf4j
public class QiniuUtil {

    /**
     * 删除七牛的图片工具 <br/>
     *
     * @param accessKey <br/>
     * @param secretKey <br/>
     * @param bucket    <br/>
     * @param images    <br/>
     * @return boolean?成功:失败
     */
    private static boolean deleteImg(String accessKey, String secretKey, String bucket, String... images) {
        boolean deleteStatus = false;
        Configuration cfg = new Configuration(Region.autoRegion());
        Auth auth = Auth.create(accessKey, secretKey);
        try {
            BucketManager bucketManager = new BucketManager(auth, cfg);
            BucketManager.BatchOperations batchOperations = new BucketManager.BatchOperations();
            batchOperations.addDeleteOp(bucket, images);
            Response response = bucketManager.batch(batchOperations);
            BatchStatus[] batchStatusList = response.jsonToObject(BatchStatus[].class);
            BatchStatus status;
            for (int i = 0; i < images.length; i++) {
                status = batchStatusList[i];
                deleteStatus = status.code == 200;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return deleteStatus;

    }

    /**
     * 对外部开发删除图片接口
     *
     * @param affectedRows <br/>
     * @param imgList      <br/>
     * @return boolean?删除成功:删除失败
     */
    public static boolean delete(String accessKey, String secretKey, String bucket, int affectedRows, List<Img> imgList) {
        String[] imgUrlArray = new String[imgList.size()];
        for (int i = 0; i < imgList.size(); i++) {
            imgUrlArray[i] = imgList.get(i).getImgUrl();
        }
        //如果删除帖子成功则删除七牛的照片
        if (affectedRows > 0 && imgUrlArray.length > 0) {
            return deleteImg(accessKey, secretKey, bucket, imgUrlArray);
        }
        return false;
    }

    public static String transferAvatarUrl(String accessKey, String secretKey, String bucket, String avatarUrl, String key) {
        Configuration configuration = new Configuration(Region.autoRegion());
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, configuration);
        try {
            FetchRet fetchRet = bucketManager.fetch(avatarUrl, bucket, key);
            if (fetchRet.key.equals(key)) {
                return fetchRet.key;
            }
        } catch (QiniuException ex) {
            System.err.println(ex.response.toString());
        }
        return "";
    }
}
