package net.gupt.community.controller;

import lombok.extern.slf4j.Slf4j;
import net.gupt.community.annotation.AuthToken;
import net.gupt.community.entity.CodeMsg;
import net.gupt.community.entity.Likes;
import net.gupt.community.entity.Result;
import net.gupt.community.entity.Student;
import net.gupt.community.service.LikesService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <h3>gupt-community</h3>
 * <p>点赞web控制层</p>
 *
 * @author : Cui
 * @date : 2019-07-30 19:17
 **/
@Slf4j
@AuthToken
@RestController
@RequestMapping(value = "/likes", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class LikesController {

    private final LikesService likesService;
    private final HttpServletRequest request;

    public LikesController(LikesService likesService, HttpServletRequest request) {
        this.likesService = likesService;
        this.request = request;
    }

    /**
     * 发表点赞
     *
     * @param likes Likes实体对象
     * @return Result
     */
    @PostMapping(value = "/postLikeOrView", produces = "application/json")
    public Result postLikes(@RequestBody Likes likes) {
        Student student = Student.student(request);
        likes.setUid(student.getUid());
        int executeResult = likesService.postLikes(likes);
        return executeResult > 0 ? Result.success(CodeMsg.SUCCESS) : Result.error(CodeMsg.FAILED);
    }

    /**
     * 查询是否点过赞或浏览过
     *
     * @param articleId   <br/>
     * @param articleType <br/>
     * @return Result <br/>
     */
    @GetMapping(value = "/isLikesOrView")
    public Result isLikes(@RequestParam(value = "articleId") Integer articleId,
                          @RequestParam(value = "articleType") Byte articleType) {
        Student student = Student.student(request);
        Integer uid = student.getUid();
        String info = request.getParameter("info");
        if (info == null) {
            Likes likes = likesService.findIsLikes(articleId, articleType, uid);
            return responseResult(likes);
        } else {
            Likes likes = likesService.findIsLikes(articleId, articleType, uid, info);
            return responseResult(likes);
        }
    }

    /**
     * 删除点赞
     *
     * @param articleType 文章类型
     * @param articleId   文章唯一标识
     * @return Result
     */
    @RequestMapping(value = "/deleteLikes", method = RequestMethod.GET)
    public Result deleteLikes(@RequestParam(value = "articleId") Integer articleId,
                              @RequestParam(value = "articleType") Byte articleType) {
        Student student = Student.student(request);
        Integer uid = student.getUid();
        int executeResult = likesService.deleteLikes(articleId, articleType, uid);
        return executeResult > 0 ? Result.success(CodeMsg.SUCCESS) : Result.error(CodeMsg.MISSING_RECORD, false);
    }

    /**
     * 获取点赞数量
     *
     * @param articleId   <br/>
     * @param articleType <br/>
     * @return Result
     */
    @GetMapping(value = "/getLikesOrViews")
    public Result getLikes(@RequestParam(value = "articleId") Integer articleId,
                           @RequestParam(value = "articleType") Byte articleType,
                           @RequestParam(value = "info", required = false) String info) {
        //判断参数是否存在info，如果不存在调用获取点赞数量，反之则调用获取浏览量
        if (info == null) {
            Likes likes = likesService.getLikes(articleId, articleType);
            return responseResult(likes, likes.getLoveNum());
        } else {
            Likes likes = likesService.getLikes(articleId, articleType, info);
            return responseResult(likes, likes.getViewNum());
        }
    }


    /**
     * 结果输出函数
     *
     * @param result <br/>
     * @return Result
     */
    private Result responseResult(Likes result) {
        return result != null ?
                Result.success(CodeMsg.SUCCESS, true) : Result.error(CodeMsg.MISSING_RECORD, false);
    }

    private Result responseResult(Likes result, Integer data) {
        assert result != null;
        return (result.getLoveNum() > 0 || result.getViewNum() > 0) ?
                Result.success(CodeMsg.SUCCESS, data) : Result.error(CodeMsg.MISSING_RECORD, data);
    }

}
