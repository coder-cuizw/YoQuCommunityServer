package net.gupt.community.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.gupt.community.entity.Msg;
import net.gupt.community.mapper.MsgMapper;
import net.gupt.community.service.MsgService;
import org.springframework.stereotype.Service;

/**
 * <h3>gupt-community</h3>
 * <p>私信业务层接口实现类</p>
 *
 * @author : Cui
 * @date : 2019-07-30 21:21
 **/
@Service
public class MsgServiceImpl implements MsgService {

    private final MsgMapper msgMapper;

    public MsgServiceImpl(MsgMapper msgMapper) {
        this.msgMapper = msgMapper;
    }

    @Override
    public PageInfo<Msg> getByReceiver(Integer receiverId,
                                       Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(msgMapper.findMsgByReceiver(receiverId));
    }

    @Override
    public PageInfo<Msg> getByPoster(Integer posterId,
                                     Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(msgMapper.findMsgByPoster(posterId));
    }

    @Override
    public PageInfo<Msg> getByPosterAndReceiver(Integer posterId, Integer receiverId,
                                                Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(msgMapper.findMsgByReceiverAndPoster(posterId, receiverId));
    }
}
