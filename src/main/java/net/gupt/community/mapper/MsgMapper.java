package net.gupt.community.mapper;

import net.gupt.community.entity.Msg;

import java.util.List;

public interface MsgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Msg record);

    int insertSelective(Msg record);

    Msg selectByPrimaryKey(Integer id);

    List<Msg> findMsgByReceiver(Integer receiverId);

    List<Msg> findMsgByPoster(Integer posterId);

    List<Msg> findMsgByReceiverAndPoster(Integer posterId, Integer receiverId);

    int updateByPrimaryKeySelective(Msg record);

    int updateByPrimaryKeyWithBLOBs(Msg record);

    int updateByPrimaryKey(Msg record);
}