package net.gupt.community.controller;

import net.gupt.community.annotation.AuthToken;
import net.gupt.community.annotation.LimitFrequency;
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
@AuthToken
@RestController
@RequestMapping(value = "/likes", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class LikesController {
    /**
     * Description 点赞，取消点赞，获取点赞 <br/>
     *
     * @author YG <br/>
     * @date 2019/9/21 10:11<br/>
     */

    private final LikesService likesService;
    private final String studentObject = "Student";

    private final int FAIL_MSG = 40004;
    public LikesController(LikesService likesService, Student student) {
        this.likesService = likesService;

    }

    /**
     * 查询是否点过赞或浏览过
     *
     * @param articleId   <br/>
     * @param articleType <br/>
     * @param request     <br/>
     * @return
     */
    @GetMapping(value = "/isLikesOrView")
    public Result isLikes(@RequestParam(value = "articleId") Integer articleId,
                          @RequestParam(value = "articleType") Byte articleType, HttpServletRequest request) {
        Student student = (Student) request.getAttribute(studentObject);
        Integer uid =student.getUid();
        String info = request.getParameter("info");
        Likes result;
        /**
         * 判断是否存在info参数
         * 存在：调用统计view的数量
         * 不存在：调用统计love的数量
         */
        if (info == null) {
            result = likesService.findIsLikes(articleId, articleType, uid);
            if (result != null) {
                return Result.success(CodeMsg.SUCCESS, "true");
            } else {
                return Result.error(FAIL_MSG, "false");
            }
        } else {
            result = likesService.findIsLikes(articleId, articleType, uid, info);
            if (result != null) {
                return Result.success(CodeMsg.SUCCESS, "true");
            } else {
                return Result.error(FAIL_MSG, "false");
            }
        }


    }


    /**
     * 获取点赞数量
     *
     * @param articleId
     * @param articleType
     * @return
     */
    @GetMapping(value = "/getLikesOrViews")
    @LimitFrequency(count = 5)
    public Result getLikes(@RequestParam(value = "articleId") Integer articleId,
                           @RequestParam(value = "articleType") Byte articleType,
                           @RequestParam(value = "info", required = false) String info) {
        /**
         * 判断参数是否存在info，如果不存在调用获取点赞数量，如果存在则调用获取浏览量
         */
        if (info == null) {
            Likes likes = likesService.getLikes(articleId, articleType);
            if (likes.getLoveNum() > 0) {
                return Result.success(CodeMsg.SUCCESS, likes);
            } else {
                return Result.error(FAIL_MSG, likes.getLoveNum().toString());
            }
        } else {
            Likes likes = likesService.getLikes(articleId, articleType, info);
            if (likes.getViewNum() > 0) {
                return Result.success(CodeMsg.SUCCESS, likes);
            } else {
                return Result.error(FAIL_MSG, likes.getViewNum().toString());
            }

        }


    }


    /**
     * 发表点赞
     *
     * @param likes
     * @return
     */
    @LimitFrequency(count = 3)
    @PostMapping(value = "/postLikeOrView", produces = "application/json")
    public Result postLikes(@RequestBody Likes likes) {
        int executeResult = likesService.postLikes(likes);
        if (executeResult > 0) {
            return Result.success(CodeMsg.SUCCESS);
        } else {
            return Result.error(CodeMsg.FAILED);
        }
    }

    /**
     * 删除点赞
     *
     * @param articleType
     * @param articleId
     * @return
     */
    @RequestMapping(value = "/deleteLikes", method = RequestMethod.GET)
    public Result deleteLikes(@RequestParam(value = "articleId") Integer articleId,
                              @RequestParam(value = "articleType") Byte articleType, HttpServletRequest request) {
        Student student = (Student) request.getAttribute(studentObject);
        Integer uid =student.getUid();
        int executeResult = likesService.deleteLikes(articleId, articleType, uid);
        if (executeResult > 0) {
            return Result.success(CodeMsg.SUCCESS);
        } else {
            return Result.error(FAIL_MSG,"不存在该记录");
        }
    }

}
