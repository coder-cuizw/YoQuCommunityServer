package net.gupt.community.service;

import net.gupt.community.entity.Likes;

/**
 * 点赞服务层接口
 *
 * @author Cui
 */
public interface LikesService {
    /**
     * 获取点赞数量
     *
     * @param articleId
     * @param articleType
     * @return
     */
    Likes getLikes(Integer articleId, Byte articleType);

    /**
     * 重载方法获取浏览量
     *
     * @param articleId
     * @param articleType
     * @param info        为了区分调用那个方法
     * @return
     */
    Likes getLikes(Integer articleId, Byte articleType, String info);

    /**
     * 检验是否已经点赞
     */
    Likes findIsLikes(Integer articleId, Byte articleType, String openId);
    /**
     * 检验是否已经浏览过
     */
    Likes findIsLikes(Integer articleId, Byte articleType, String openId,String info);
    /**
     * 发表点赞
     *
     * @param likes
     * @return
     */
    int postLikes(Likes likes);

    /**
     * 取消点赞
     *
     * @param articleId
     * @param articleType
     * @return
     */
    int deleteLikes(Integer articleId, Byte articleType, String openId);
}
