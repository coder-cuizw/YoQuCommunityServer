package net.gupt.community.mapper;

import net.gupt.community.entity.Likes;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface LikesMapper {

    /**
     * 删除点赞
     *
     * @param uid 学号
     * @param articleId 帖子Id
     * @return 执行结果
     */
    int deleteLikes(Integer uid,Integer articleId);

    /**
     * 发表点赞
     *
     * @param uid 学号
     * @param articleId 帖子Id
     * @return 执行结果
     */
    int insertLikes(Integer uid,Integer articleId);

    /**
     * 通过articleId获得点赞列表
     *
     * @param articleId 帖子Id
     * @return 点赞表
     */
    List<Likes> findLikesByArticleId(Integer articleId);

    int insertSelective(Likes record);

    Likes selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Likes record);

    int updateByPrimaryKey(Likes record);
}