package net.gupt.community.service;

import com.github.pagehelper.PageInfo;
import net.gupt.community.entity.Msg;
import net.gupt.community.entity.Result;
import net.gupt.community.entity.Student;

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
     * @return PageInfo<Msg>
     */
    PageInfo<Msg> getByReceiver(Integer receiverUid,
                                Integer pageNum, Integer pageSize);


    /**
     * Description 发送信息<br/>
     *
     * @param msg     <br/>
     * @param student <br/>
     * @return result
     * @author YG <br/>
     * @date 2019/12/5 20:35<br/>
     */
    Result postMsg(Msg msg, Student student);

}
