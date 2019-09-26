package net.gupt.community.controller;

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
@AuthToken
@RestController
@RequestMapping(value = "/likes", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class LikesController {
    /**
     * Description 点赞，取消点赞，获取点赞 <br/>
     * @author  YG<br/>
     * @date   2019/9/21 10:11<br/>
     */

    private final LikesService likesService;

    public LikesController(LikesService likesService, Student student) {
        this.likesService = likesService;

    }



    /**
     * 获取点赞列表
     * @param articleId
     * @param articleType
     * @return
     */
    @GetMapping(value = "/getLikes")
    public Result getLikes(@RequestParam(value = "articleId") Integer articleId,
                              @RequestParam(value = "articleType") Byte articleType) {
        Likes likes = likesService.getLikes(articleId,articleType);
        if (likes == null) {
            return Result.error(CodeMsg.FAILED);
        }
        return Result.success(CodeMsg.SUCCESS,likes);
    }

    /**
     * 发表点赞
     * @param likes
     * @return
     */
    @PostMapping(value = "/postLikes",produces = "application/json")
    public Result postLikes(@RequestBody Likes likes){
        int executeResult = likesService.postLikes(likes);
        if (executeResult > 0){
            return Result.success(CodeMsg.SUCCESS);
        }else{
            return Result.error(CodeMsg.FAILED);
        }
    }

    /**
     * 删除点赞
     * @param articleType
     * @param articleId
     * @return
     */
    @RequestMapping(value = "/deleteLikes",method = RequestMethod.GET)
    public Result deleteLikes(@RequestParam(value = "articleId") Integer articleId,
                              @RequestParam(value = "articleType") Byte articleType, HttpServletRequest request){
        String  openId  =  request.getAttribute("OPEN_ID").toString();
        int executeResult = likesService.deleteLikes(articleId,articleType,openId);
        if (executeResult > 0){
            return Result.success(CodeMsg.SUCCESS);
        }else{
            return Result.error(CodeMsg.FAILED);
        }
    }

}
