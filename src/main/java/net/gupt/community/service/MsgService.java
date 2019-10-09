package net.gupt.community.service;

import com.github.pagehelper.PageInfo;
import net.gupt.community.entity.Msg;

/**
 * 私信业务层
 *
 * @author Cui
 */
public interface MsgService {
    /**
     * 获取接收人
     *
     * @param receiverId <br/>
     * @param pageNum    <br/>
     * @param pageSize   <br/>
     * @return PageInfo<Msg>
     */
    PageInfo<Msg> getByReceiver(Integer receiverId, Integer pageNum, Integer pageSize);

    /**
     * 获取发送人
     *
     * @param posterId <br/>
     * @param pageNum  <br/>
     * @param pageSize <br/>
     * @return PageInfo<Msg>
     */
    PageInfo<Msg> getByPoster(Integer posterId, Integer pageNum, Integer pageSize);

    /**
     * 获取收信人和接受人
     *
     * @param posterId   <br/>
     * @param receiverId <br/>
     * @param pageNum    <br/>
     * @param pageSize   <br/>
     * @return PageInfo<Msg>
     */
    PageInfo<Msg> getByPosterAndReceiver(Integer posterId, Integer receiverId,
                                         Integer pageNum, Integer pageSize);

}
