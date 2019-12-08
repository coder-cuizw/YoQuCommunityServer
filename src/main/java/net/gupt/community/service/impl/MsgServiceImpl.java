package net.gupt.community.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import net.gupt.community.controller.websocket.WebSocketMsgController;
import net.gupt.community.entity.CodeMsg;
import net.gupt.community.entity.Msg;
import net.gupt.community.entity.Result;
import net.gupt.community.entity.Student;
import net.gupt.community.mapper.MsgMapper;
import net.gupt.community.service.MsgService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h3>gupt-community</h3>
 * <p>私信业务层接口实现类</p>
 *
 * @author : Cui
 * @date : 2019-07-30 21:21
 **/
@Slf4j
@Service
public class MsgServiceImpl implements MsgService {

    private final MsgMapper msgMapper;


    public MsgServiceImpl(MsgMapper msgMapper) {
        this.msgMapper = msgMapper;
    }

    @Override
    public synchronized PageInfo<Msg> getByReceiver(Integer receiverUid,
                                                    Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Msg> msgByReceiver = msgMapper.findMsgByReceiver(receiverUid);
        msgMapper.deleteMsg(receiverUid);
        return new PageInfo<>(msgByReceiver);
    }

    @Override
    public Result postMsg(Msg msg, Student student) {
        List<Integer> onlineUser = WebSocketMsgController.getOnlineUser();
        log.info("在线用户" + onlineUser);
        msg.setPosterUid(student.getUid());
        int insert = msgMapper.insert(msg);
        if (insert > 0 && onlineUser.contains(msg.getReceiverUid())) {
            // 将数据库返回的id设置进对象中
            msg.setId(msg.getId());
            Result<Msg> msgNotify = Result.success(CodeMsg.MSG_NOTIFY, msg);
            WebSocketMsgController.sendToUser(msgNotify);
            return Result.success(CodeMsg.SUCCESS);
        } else {
            return Result.error(CodeMsg.OFF_LINE);
        }
    }


}
