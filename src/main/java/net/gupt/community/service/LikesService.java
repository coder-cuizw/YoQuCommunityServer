package net.gupt.community.service;

import com.github.pagehelper.PageInfo;
import net.gupt.community.entity.Likes;

import java.util.List;

/**
 * 点赞服务层接口
 *
 * @author Cui
 */
public interface LikesService {
    /**
     * 获取点赞列表
     * @param articleId
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Likes> getLikes(Integer articleId, Integer pageNum, Integer pageSize);

    /**
     * 发表点赞
     * @param uid
     * @param articleId
     * @return
     */
    int postLikes(Integer uid,Integer articleId);

    /**
     * 取消点赞
     * @param uid
     * @param articleId
     * @return
     */
    int deleteLikes(Integer uid,Integer articleId);
}
