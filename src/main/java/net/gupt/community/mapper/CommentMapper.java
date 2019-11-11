package net.gupt.community.mapper;

import net.gupt.community.entity.Comment;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 评论Mapper
 *
 * @author Cui
 */
@Component
public interface CommentMapper {
    /**
     * 删除帖子的所有评论
     *
     * @param articleId   帖子Id
     * @param articleType 帖子类型
     * @return int
     */
    int deleteByArticleId(Integer articleId, Integer articleType);

    /**
     * 删除单条评论
     *
     * @param id  评论的Id
     * @param uid 学号
     * @return int
     */
    int deleteByPrimaryId(Integer id, Integer uid);

    /**
     * 发表评论
     *
     * @param comment 传入
     * @return int
     */
    int insertByComment(Comment comment);

    /**
     * Description 通过articleId获得评论列表 <br/>
     *
     * @param type      <br/>
     * @param articleId <br/>
     * @return List<Comment></Comment>
     * @author YG <br/>
     * @date 2019/10/8 13:37<br/>
     */
    List<Comment> findCommentsByArticleId(Byte type, Integer articleId);


    /**
     * 删除回复的评论
     *
     * @param id 主键Id
     * @return int
     */
    int deleteReply(Integer id);

}