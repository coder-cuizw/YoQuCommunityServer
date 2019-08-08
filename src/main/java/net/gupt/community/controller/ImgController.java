package net.gupt.community.controller;

import com.github.pagehelper.PageInfo;
import net.gupt.community.annotation.AuthToken;
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
@AuthToken
@RestController
@RequestMapping(value = "/img", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ImgController {

    private final ImgService imgService;

    public ImgController(ImgService imgService) {
        this.imgService = imgService;
    }

    @RequestMapping(value = "/getImgs", method = RequestMethod.GET)
    public Result getImgs(@RequestParam(value = "articleId") Integer articleId,
                          @RequestParam(value = "pageNum") Integer pageNum,
                          @RequestParam(value = "pageSize") Integer pageSize) {
        PageInfo<Img> imgPageInfo = imgService.getImgs(articleId, pageNum, pageSize);
        if (imgPageInfo == null) {
            return Result.error(CodeMsg.FAILED);
        }
        return Result.success(CodeMsg.SUCCESS, new PageInfoBean<>(imgPageInfo));
    }

}
