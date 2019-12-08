package net.gupt.community.mapper;

import net.gupt.community.entity.Msg;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description 此接口没有投入使用<br/>
 *
 * @author YG <br/>
 * @date 2019/10/8 16:32<br/>
 * @return 无
 */
@Component
public interface MsgMapper {

    /**
     * Description 发送私信 <br/>
     *
     * @param record <br/>
     * @return int
     * @author YG <br/>
     * @date 2019/12/6 15:15<br/>
     */
    int insert(Msg record);


    /**
     * Description 查询信息<br/>
     *
     * @param receiverUid <br/>
     * @return List
     * @author YG <br/>
     * @date 2019/12/6 15:07<br/>
     */
    List<Msg> findMsgByReceiver(Integer receiverUid);


    /**
     * Description 更新私信 <br/>
     *
     * @param record <br/>
     * @return int
     * @author YG <br/>
     * @date 2019/12/6 15:17<br/>
     */
    int updateByPrimaryKey(Msg record);

    /**
     * 删除信息
     *
     * @param receiverUid 学号
     * @return int
     */
    int deleteMsg(Integer receiverUid);
}