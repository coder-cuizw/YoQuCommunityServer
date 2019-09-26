package net.gupt.community.service;

import net.gupt.community.entity.Likes;

/**
 * 点赞服务层接口
 *
 * @author Cui
 */
public interface LikesService {
    /**
     * 获取点赞列表
     * @param articleId
     * @param articleType
     * @return
     */
     Likes getLikes(Integer articleId , Byte articleType);

    /**
     * 发表点赞
     * @param likes
     * @return
     */
    int postLikes(Likes likes);

    /**
     * 取消点赞
     * @param articleId
     * @param articleType
     * @return
     */
    int deleteLikes(Integer articleId,Byte articleType,String openId,Byte state);
}
