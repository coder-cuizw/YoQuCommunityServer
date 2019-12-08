package net.gupt.community.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import net.gupt.community.annotation.AuthToken;
import net.gupt.community.entity.*;
import net.gupt.community.service.MsgService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <h3>gupt-community</h3>
 * <p>私信Web控制层</p>
 *
 * @author : Cui
 * @date : 2019-07-30 21:26
 **/
@Slf4j
@AuthToken
@Api(value = "私信", protocols = "http", tags = "私信接口")
@RestController
@RequestMapping(value = "msg", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MsgController {

    private final MsgService msgService;

    public MsgController(MsgService msgService) {
        this.msgService = msgService;
    }

    /**
     * Description 发送私信 <br/>
     *
     * @param msg <br/>
     * @return Result
     * @author YG <br/>
     * @date 2019/12/5 17:27<br/>
     */
    @PostMapping("/sendMsg")
    public Result sendMsg(@RequestBody Msg msg) {
        return msgService.postMsg(msg);
    }

    @GetMapping("/getUnreadMessage")
    public Result getUnreadMessage(@RequestParam(value = "pageNum") Integer pageNum,
                                   @RequestParam(value = "pageSize") Integer pageSize,
                                   @RequestParam(value = "msgType") Byte msgType,
                                   HttpServletRequest request) {
        Student student = (Student) request.getAttribute("Student");
        PageInfo<Msg> byReceiver = msgService.getByReceiver(student.getUid(), pageNum, pageSize, msgType);
        return byReceiver == null ? Result.error(CodeMsg.FAILED) : Result.success(CodeMsg.SUCCESS, new PageInfoBean<>(byReceiver));
    }
}
