package net.gupt.community.controller;

import com.github.pagehelper.PageInfo;
import net.gupt.community.annotation.AuthToken;
import net.gupt.community.entity.CodeMsg;
import net.gupt.community.entity.Msg;
import net.gupt.community.entity.Result;
import net.gupt.community.service.MsgService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * <h3>gupt-community</h3>
 * <p>私信Web控制层</p>
 *
 * @author : Cui
 * @date : 2019-07-30 21:26
 **/
@AuthToken
@RestController
@RequestMapping(value = "/msg", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MsgController {

    private final MsgService msgService;

    public MsgController(MsgService msgService) {
        this.msgService = msgService;
    }

    @RequestMapping(value = "/getMsg", method = RequestMethod.GET)
    public Result getMsg(@RequestParam(value = "posterId", required = false) Integer posterId,
                         @RequestParam(value = "receiverId", required = false) Integer receiverId,
                         @RequestParam(value = "pageNum") Integer pageNum,
                         @RequestParam(value = "pageSize") Integer pageSize) {
        PageInfo<Msg> msgPageInfo = null;
        if (posterId != null & receiverId != null) {
            msgPageInfo = msgService.getByPosterAndReceiver(posterId,
                    receiverId, pageNum, pageSize);
        } else if (posterId != null) {
            msgPageInfo = msgService.getByPoster(posterId,
                    pageNum, pageSize);
        } else if (receiverId != null) {
            msgPageInfo = msgService.getByReceiver(receiverId,
                    pageNum, pageSize);
        } else {
            return Result.error(CodeMsg.REQUEST_ILLEGAL);
        }
        if (msgPageInfo == null) {
            return Result.error(CodeMsg.FAILED);
        }
        return Result.success(CodeMsg.SUCCESS, msgPageInfo);
    }

}
