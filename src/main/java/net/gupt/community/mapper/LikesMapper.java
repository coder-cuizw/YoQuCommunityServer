package net.gupt.community.mapper;

import net.gupt.community.entity.Likes;
import org.springframework.stereotype.Component;
@Component
public interface LikesMapper {

    /**
     * Description 获取点赞数和浏览量<br/>
     *
     * @param articleId   帖子ID <br/>
     * @param articleType 帖子类型<br/>
     * @return Likes
     * @date 2019/9/20 16:00<br/>
     */
    Likes findLikes(Integer articleId, Byte articleType);

    /**
     * 获取 浏览量
     *
     * @param articleId   文章ID
     * @param articleType 文章类型
     * @return Likes
     */
    Likes findView(Integer articleId, Byte articleType);

    /**
     * 删除点赞
     *
     * @param articleType 帖子类型
     * @param articleId   帖子Id
     * @param uid         学号
     * @return int
     */
    int deleteLikes(Integer articleId, Byte articleType, Integer uid);

    /**
     * 发表点赞
     *
     * @param likes Likes对象
     * @return int
     */
    int insertLikes(Likes likes);

    /**
     * 检验是否有点赞记录
     *
     * @param articleId   帖子ID
     * @param articleType 帖子类型
     * @param uid         学号
     * @return Likes
     */

    Likes findIsLikes(Integer articleId, Byte articleType, Integer uid);

    /**
     * 检验是否有浏览记录
     *
     * @param articleId   帖子ID
     * @param articleType 帖子类型
     * @param uid         学号
     * @param info        页面标识
     * @return Likes
     */
    Likes findIsViews(Integer articleId, Byte articleType, Integer uid, String info);


}