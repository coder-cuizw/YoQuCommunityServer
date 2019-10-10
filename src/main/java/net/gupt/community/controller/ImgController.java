package net.gupt.community.controller;

import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import net.gupt.community.annotation.AuthToken;
import net.gupt.community.annotation.LimitFrequency;
import net.gupt.community.entity.CodeMsg;
import net.gupt.community.entity.Img;
import net.gupt.community.entity.PageInfoBean;
import net.gupt.community.entity.Result;
import net.gupt.community.service.ImgService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * <h3>gupt-community</h3>
 * <p>获取图片控制层</p>
 *
 * @author : Cui
 * @date : 2019-07-30 20:46
 **/
@Slf4j
@AuthToken
@RestController
@RequestMapping(value = "/img", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ImgController {

    private final ImgService imgService;

    public ImgController(ImgService imgService) {
        this.imgService = imgService;
    }

    /**
     * 获取图片信息
     *
     * @param articleId   帖子Id
     * @param articleType 帖子类型
     * @param pageNum     页数
     * @param pageSize    每页条数
     * @return 结果集输出信息
     */
    @RequestMapping(value = "/getImgs", method = RequestMethod.GET)
    public Result getImgs(@RequestParam(value = "articleId") Integer articleId,
                          @RequestParam(value = "articleType") Byte articleType,
                          @RequestParam(value = "pageNum") Integer pageNum,
                          @RequestParam(value = "pageSize") Integer pageSize) {
        PageInfo<Img> imgPageInfo = imgService.getImgs(articleId, articleType, pageNum, pageSize);
        if (imgPageInfo == null) {
            return Result.error(CodeMsg.FAILED);
        }
        return Result.success(CodeMsg.SUCCESS, new PageInfoBean<>(imgPageInfo));
    }

    /**
     * 上传图片
     *
     * @param img 图片信息
     * @return 结果集输出信息
     */
    @LimitFrequency(count = 5)
    @RequestMapping(value = "/postImg", method = RequestMethod.POST, consumes = "application/json")
    public Result postImg(@RequestBody Img img) {
        int rows = imgService.postImg(img);
        if (rows > 0) {
            return Result.success(CodeMsg.POST_SUCCESS);
        } else {
            return Result.success(CodeMsg.POST_FAILED);
        }
    }
}
