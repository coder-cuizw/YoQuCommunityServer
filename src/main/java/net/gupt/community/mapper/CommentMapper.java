package net.gupt.community.mapper;

import net.gupt.community.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 评论Mapper
 *
 * @author Cui
 */
@Mapper
@Component
public interface CommentMapper {
    /**
     * 删除评论
     *
     * @param articleId   帖子Id
     * @param articleType 帖子类型
     * @return int
     */
    int deleteByArticleId(Integer articleId, Integer articleType);

    /**
     * 删除单条评论
     *
     * @param id 评论的Id
     * @return int
     */
    int deleteByPrimaryId(Integer id);

    /**
     * 发表评论
     *
     * @param comment 传入
     * @return int
     */
    int insertByComment(Comment comment);


    /**
     * Description 通过articleId获得评论列表 <br/>
     * @author  YG <br/>
     * @date   2019/10/8 13:37<br/>
     * @param  type <br/>
     * @param articleId <br/>
     * @return  List<Comment></Comment>
     */
    List<Comment> findCommentsByArticleId(Byte type, Integer articleId);

}