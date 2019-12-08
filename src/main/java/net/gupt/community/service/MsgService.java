package net.gupt.community.service;

import com.github.pagehelper.PageInfo;
import net.gupt.community.entity.Msg;
import net.gupt.community.entity.Result;

/**
 * 私信业务层
 *
 * @author Cui
 */
public interface MsgService {

    /**
     * 获取收信人和接受人
     *
     * @param receiverUid <br/>
     * @param pageNum     <br/>
     * @param pageSize    <br/>
     * @param msgType  <br/>
     * @return PageInfo<Msg>
     */
    PageInfo<Msg> getByReceiver(Integer receiverUid,
                                Integer pageNum, Integer pageSize,Byte msgType);


    /**
     * Description 发送信息<br/>
     *
     * @param msg <br/>
     * @return result
     * @author YG <br/>
     * @date 2019/12/5 20:35<br/>
     */
    Result postMsg(Msg msg);
}
