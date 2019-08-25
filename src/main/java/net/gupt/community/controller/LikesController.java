package net.gupt.community.controller;

import com.github.pagehelper.PageInfo;
import net.gupt.community.annotation.AuthToken;
import net.gupt.community.entity.CodeMsg;
import net.gupt.community.entity.Likes;
import net.gupt.community.entity.PageInfoBean;
import net.gupt.community.entity.Result;
import net.gupt.community.service.LikesService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * <h3>gupt-community</h3>
 * <p>点赞web控制层</p>
 *
 * @author : Cui
 * @date : 2019-07-30 19:17
 **/
@AuthToken
@RestController
@RequestMapping(value = "/likes", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class LikesController {

    private final LikesService likesService;

    public LikesController(LikesService likesService) {
        this.likesService = likesService;
    }

    /**
     * 获取点赞列表
     * @param articleId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getLikes", method = RequestMethod.GET)
    public Result getLikes(@RequestParam(value = "articleId") Integer articleId,
                           @RequestParam(value = "pageNum") Integer pageNum,
                              @RequestParam(value = "pageSize") Integer pageSize) {
        PageInfo<Likes> likesPageInfo = likesService.getLikes(articleId, pageNum, pageSize);
        if (likesPageInfo == null) {
            return Result.error(CodeMsg.FAILED);
        }
        return Result.success(CodeMsg.SUCCESS, new PageInfoBean<>(likesPageInfo));
    }

    /**
     * 发表点赞
     * @param uid
     * @param articleId
     * @return
     */
    @RequestMapping(value = "/postLikes",method = RequestMethod.POST)
    public Result postLikes(@RequestParam(value = "uid") Integer uid,
                            @RequestParam(value = "articleId") Integer articleId){
        int executeResult = likesService.postLikes(uid,articleId);
        if (executeResult > 0){
            return Result.success(CodeMsg.SUCCESS);
        }else{
            return Result.error(CodeMsg.FAILED);
        }
    }

    /**
     * 取消点赞
     *
     * @param uid
     * @param articleId
     * @return
     */
    @RequestMapping(value = "/deleteLikes",method = RequestMethod.GET)
    public Result deleteLikes(@RequestParam(value = "uid") Integer uid,
                            @RequestParam(value = "articleId") Integer articleId){
        int executeResult = likesService.deleteLikes(uid,articleId);
        if (executeResult > 0){
            return Result.success(CodeMsg.SUCCESS);
        }else{
            return Result.error(CodeMsg.FAILED);
        }
    }
}
