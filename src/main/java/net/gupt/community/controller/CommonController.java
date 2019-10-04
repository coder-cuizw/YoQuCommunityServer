package net.gupt.community.controller;

import com.github.pagehelper.PageInfo;
import net.gupt.community.annotation.AuthToken;
import net.gupt.community.annotation.LimitFrequency;
import net.gupt.community.entity.*;
import net.gupt.community.service.CommonService;
import net.gupt.community.service.ImgService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <h3>gupt-community</h3>
 * <p>通用帖子web控制层</p>
 *
 * @author : Cui
 * @date : 2019-07-30 16:53
 **/
@AuthToken
@RestController
@RequestMapping(value = "/common", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CommonController {

    private final CommonService commonService;

    private final ImgService imgService;

    public CommonController(CommonService commonService, ImgService imgService) {
        this.commonService = commonService;
        this.imgService = imgService;
    }

    /**
     * 获取帖子数据
     *
     * @param postType 帖子类型
     * @param pageNum  页数
     * @param pageSize 每页条数
     * @return 结果集输出信息
     */
    @RequestMapping(value = "/getArticles", method = RequestMethod.GET)
    public Result getArticles(@RequestParam(value = "postType") Byte postType,
                              @RequestParam(value = "pageNum") Integer pageNum,
                              @RequestParam(value = "pageSize") Integer pageSize,
                              @RequestParam(value = "isTop",required = false)Boolean isTop,
                              @RequestParam(value = "id", required = false) Integer id) {
        PageInfo<Common> articles = commonService.getArticles(postType, pageNum, pageSize, null, id,isTop);
            if (articles == null) {
            return Result.error(CodeMsg.FAILED);
        }
        return Result.success(CodeMsg.SUCCESS, new PageInfoBean<>(articles));
    }

    /**
     * 发表帖子 整合发送图片
     *
     * @param common 帖子信息
     * @return 结果集输出信息
     */
    @LimitFrequency(count = 3)
    @RequestMapping(value = "/postArticle", method = RequestMethod.POST)
    public Result postArticle(@RequestBody Common common,Img imgObject) {
        int result = commonService.postArticle(common);
        /***
         * 发帖失败返回
         */
        if (result == 0) {
            return Result.error(CodeMsg.FAILED);
        } else if (common.getImg() != null) {
            // 获取文章id 和文章类型
            Integer id = common.getId();
            Byte postType = common.getPostType();
            List<Img> imgs = common.getImg();
            //遍历对象吗，并将文章id赋值给imgs;
            for (Img img : imgs
            ) {
                img.setArticleId(id);
                img.setArticleType(postType);
                imgObject = img;
            }
            //如果传入的ingUrl为非空子符传则调用发送图片接口
            if(!imgObject.getImgUrl().trim().equals("")) {
                imgService.postImg(imgObject);
            }
        }
        return Result.success(CodeMsg.SUCCESS, common.getId());

    }

    /**
     * 设置置顶
     * @param common
     * @return
     */
    @PostMapping("/setTop")
    public Result setTop(@RequestBody Common common){
        int result = commonService.setTop(common);
        if (result == 0) {
            return Result.error(CodeMsg.FAILED);
        }
        return Result.success(CodeMsg.SUCCESS);
    }

    /**
     * 删除帖子及相关数据
     *
     * @param id 帖子Id
     * @return 结果集输出信息
     */
    @RequestMapping(value = "/deleteArticle", method = RequestMethod.GET)
    public Result deleteArticle(@RequestParam("articleType") Integer articleType,
                                @RequestParam("id") Integer id) {
        int result = commonService.deleteArticle(articleType, id);
        if (result == 0) {
            return Result.error(CodeMsg.FAILED);
        }
        return Result.success(CodeMsg.SUCCESS);
    }

}
