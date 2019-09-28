package net.gupt.community.controller;

import com.github.pagehelper.PageInfo;
import net.gupt.community.annotation.AuthToken;
import net.gupt.community.entity.*;
import net.gupt.community.service.CommonService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    public CommonController(CommonService commonService) {
        this.commonService = commonService;
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
    public Result getArticles(@RequestParam(value = "postType") Integer postType,
                              @RequestParam(value = "pageNum") Integer pageNum,
                              @RequestParam(value = "pageSize") Integer pageSize,
                              @RequestParam(value = "id",required = false)Integer id) {
        PageInfo<Common> articles = commonService.getArticles(postType, pageNum, pageSize, null,id);
        if (articles == null) {
            return Result.error(CodeMsg.FAILED);
        }
        return Result.success(CodeMsg.SUCCESS, new PageInfoBean<>(articles));
    }

    /**
     * 发表帖子
     *
     * @param common 帖子信息
     * @return 结果集输出信息
     */
    @RequestMapping(value = "/postArticle", method = RequestMethod.POST)
    public Result postArticle(@RequestBody Common common) {
        int result = commonService.postArticle(common);
        if (result == 0) {
            return Result.error(CodeMsg.FAILED);
        }
        return Result.success(CodeMsg.SUCCESS, common.getId());
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
