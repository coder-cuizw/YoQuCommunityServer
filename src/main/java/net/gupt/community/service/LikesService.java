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
     * @param articleId   文章ID
     * @param articleType 文章类型
     * @return Likes
     */
    Likes getLikes(Integer articleId, Byte articleType);

    /**
     * 重载方法获取浏览量
     *
     * @param articleId   文章ID
     * @param articleType 文章类型
     * @param info        为了区分调用那个方法
     * @return Likes
     */
    Likes getLikes(Integer articleId, Byte articleType, String info);

    /**
     * 检验是否已经点赞
     *
     * @param articleId   文章ID
     * @param articleType 文章类型
     * @param uid         学号
     * @return Likes
     */
    Likes findIsLikes(Integer articleId, Byte articleType, Integer uid);

    /**
     * 检验是否已经浏览过
     *
     * @param articleType 文章类型
     * @param articleId   文章ID
     * @param uid         学号
     * @param info        页面标识
     * @return Likes
     */
    Likes findIsLikes(Integer articleId, Byte articleType, Integer uid, String info);

    /**
     * 发表点赞
     *
     * @param likes Likes对象
     * @return Likes
     */
    int postLikes(Likes likes);

    /**
     * 取消点赞
     *
     * @param articleId   文章ID
     * @param articleType 文章类型
     * @param uid         学号
     * @return int
     */
    int deleteLikes(Integer articleId, Byte articleType, Integer uid);

    /**
     * 同时获取点赞量和浏览量
     *
     * @param articleId   文章ID
     * @param articleType 文章类型
     * @param likes       点赞对象
     * @return findLovesAndViews
     */
    Likes findLovesAndViews(Integer articleId, Byte articleType, Likes likes);
}
