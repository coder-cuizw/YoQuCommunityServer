package net.gupt.community.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import net.gupt.community.annotation.AuthToken;
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
@Api(value = "点赞", protocols = "http", tags = "点赞接口")
@RestController
@RequestMapping(value = "/likes", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class LikesController {

    private final LikesService likesService;
    private final HttpServletRequest request;
    private final String stu = "Student";

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
    @PostMapping(value = "/postLikeOrView")
    public Result<?> postLikes(@RequestBody Likes likes) {
        Student student = (Student) request.getAttribute("Student");
        likes.setUid(student.getUid());
        return likesService.postLikes(likes);
    }

    /**
     * 查询是否点过赞或浏览过
     *
     * @param articleId   <br/>
     * @param articleType <br/>
     * @return Result <br/>
     */
    @GetMapping(value = "/isLikesOrView")
    public Result<?> isLikes(@RequestParam(value = "articleId") Integer articleId,
                          @RequestParam(value = "articleType") Byte articleType,
                          @RequestParam(value = "info", required = false) String info) {
        Student student = (Student) request.getAttribute(stu);
        Integer uid = student.getUid();
        if (info == null) {
            return likesService.findIsLikes(articleId, articleType, uid);
        } else {
            return likesService.findIsLikes(articleId, articleType, uid, info);
        }
    }

    /**
     * 删除点赞
     *
     * @param articleType 文章类型
     * @param articleId   文章唯一标识
     * @return Result
     */
    @DeleteMapping("/deleteLikes")
    public Result deleteLikes(@RequestParam(value = "articleId") Integer articleId,
                              @RequestParam(value = "articleType") Byte articleType) {
        Student student = (Student) request.getAttribute(stu);
        Integer uid = student.getUid();
        return likesService.deleteLikes(articleId, articleType, uid);
    }

    /**
     * 更新点赞状态
     * @param likes 点赞
     * @return result
     */
    @PostMapping("/updateLikeStatus")
    public Result updateLikeStatus(@RequestBody Likes likes) {
        Student student = (Student) request.getAttribute(stu);
        return likesService.updateLikeStatus(student, likes);
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
            return likesService.getLikes(articleId, articleType);

        } else {
            return likesService.getLikes(articleId, articleType, info);
        }
    }


}
