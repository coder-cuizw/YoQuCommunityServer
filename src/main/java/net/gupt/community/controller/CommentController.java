package net.gupt.community.controller;

import com.github.pagehelper.PageInfo;
import net.gupt.community.annotation.AuthToken;
import net.gupt.community.entity.CodeMsg;
import net.gupt.community.entity.Comment;
import net.gupt.community.entity.PageInfoBean;
import net.gupt.community.entity.Result;
import net.gupt.community.service.CommentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * <h3>gupt-community</h3>
 * <p>评论web控制层</p>
 *
 * @author : Cui
 * @date : 2019-07-30 18:49
 **/
@AuthToken
@RestController
@RequestMapping(value = "/comment", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @ResponseBody
    @RequestMapping(value = "/getComments", method = RequestMethod.GET)
    public Result getComments(@RequestParam(value = "articleId") Integer articleId,
                              @RequestParam(value = "pageNum") Integer pageNum,
                              @RequestParam(value = "pageSize") Integer pageSize) {
        PageInfo<Comment> commentPageInfo = commentService.getComments(articleId, pageNum, pageSize);
        if (commentPageInfo == null) {
            return Result.error(CodeMsg.FAILED);
        }
        return Result.success(CodeMsg.SUCCESS, new PageInfoBean<>(commentPageInfo));
    }

    @ResponseBody
    @RequestMapping(value = "/postComment",method = RequestMethod.POST)
    public Result postComment(@RequestBody Comment comment){
        int executeResult = commentService.postComment(comment);
        if (executeResult > 0){
            return Result.success(CodeMsg.SUCCESS);
        }else{
            return Result.error(CodeMsg.FAILED);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteComment",method = RequestMethod.GET)
    public Result deleteComment(@RequestParam(value = "articleId") Integer articleId){
        int executeResult = commentService.deleteComment(articleId);
        if (executeResult > 0){
            return Result.success(CodeMsg.SUCCESS);
        }else{
            return Result.error(CodeMsg.FAILED);
        }
    }


}
