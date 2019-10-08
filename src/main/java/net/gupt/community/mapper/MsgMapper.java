package net.gupt.community.mapper;

import net.gupt.community.entity.Msg;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description 此接口没有投入使用<br/>
 * @author  YG <br/>
 * @date   2019/10/8 16:32<br/>
 * @return 无
 */
@Component
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