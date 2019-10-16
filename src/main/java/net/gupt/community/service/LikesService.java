package net.gupt.community.service;

import net.gupt.community.entity.Likes;

/**
 * 点赞服务层接口
 *
 * @author Cui
 */
public interface LikesService {
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

}
