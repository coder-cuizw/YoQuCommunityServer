package net.gupt.community.service;

import com.github.pagehelper.PageInfo;
import net.gupt.community.entity.Msg;

/**
 * 私信业务层
 *
 * @author Cui
 */
public interface MsgService {

    PageInfo<Msg> getByReceiver(Integer receiverId, Integer pageNum, Integer pageSize);

    PageInfo<Msg> getByPoster(Integer posterId, Integer pageNum, Integer pageSize);

    PageInfo<Msg> getByPosterAndReceiver(Integer posterId, Integer receiverId,
                                         Integer pageNum, Integer pageSize);

}
