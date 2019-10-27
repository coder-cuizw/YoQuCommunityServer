package net.gupt.community.controller;

import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import net.gupt.community.annotation.AuthToken;
import net.gupt.community.annotation.LimitFrequency;
import net.gupt.community.entity.*;
import net.gupt.community.service.CommentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <h3>gupt-community</h3>
 * <p>评论web控制层</p>
 *
 * @author : Cui
 * @date : 2019-07-30 18:49
 **/
@Slf4j
@AuthToken
@RestController
@RequestMapping(value = "/comment", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CommentController {

    private final CommentService commentService;
    private final HttpServletRequest request;
    private final String stu = "Student";

    public CommentController(CommentService commentService, HttpServletRequest request) {
        this.commentService = commentService;
        this.request = request;
    }

    /**
     * 获取评论列表
     *
     * @param articleId 帖子Id
     * @param pageNum   页数
     * @param pageSize  每页条数
     * @return 结果集输出信息
     */

    @RequestMapping(value = "/getComments", method = RequestMethod.GET)
    public Result getComments(@RequestParam(value = "type") Byte type,
                              @RequestParam(value = "articleId") Integer articleId,
                              @RequestParam(value = "pageNum") Integer pageNum,
                              @RequestParam(value = "pageSize") Integer pageSize) {
        PageInfo<Comment> commentPageInfo = commentService.getComments(type, articleId, pageNum, pageSize);
        return commentPageInfo == null ? Result.error(CodeMsg.FAILED) : Result.success(CodeMsg.SUCCESS, new PageInfoBean<>(commentPageInfo));
    }

    /**
     * 发表评论
     *
     * @param comment 留言信息
     * @return 结果集输出信息
     */
    @LimitFrequency(count = 15)
    @RequestMapping(value = "/postComment", method = RequestMethod.POST)
    public Result postComment(@RequestBody Comment comment) {
        Student student = (Student) request.getAttribute(stu);
        comment.setUid(student.getUid());
        int executeResult = commentService.postComment(comment);
        return executeResult > 0 ? Result.success(CodeMsg.SUCCESS) : Result.error(CodeMsg.FAILED);
    }

    /**
     * 删除评论
     *
     * @param id 评论Id
     * @return 结果集输出信息
     */
    @RequestMapping(value = "/deleteComment", method = RequestMethod.GET)
    public Result deleteComment(@RequestParam(value = "id") Integer id) {
        Student student = (Student) request.getAttribute(stu);
        boolean permission = student.getPermission();
        if (permission) {
            int executeResult = commentService.deleteByPrimaryId(id);
            if (executeResult > 0) {
                return Result.success(CodeMsg.DELETE_SUCCESS);
            }
        }
        return Result.error(CodeMsg.DELETE_FAILED);
    }
}
