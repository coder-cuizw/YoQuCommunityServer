package net.gupt.community.mapper;

import net.gupt.community.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
@Component
public interface CommentMapper {
    /**
     * 删除评论
     * @param articleId
     * @param articleType
     * @return int
     */
    int deleteByArticleId(Integer articleId,Integer articleType);

    /**
     * 发表评论
     * @param comment
     * @return int
     */
    int insertByComment(Comment comment);

    /**
     * 通过articleId获得评论列表
     * @param articleId
     * @return List
     */
    List<Comment> findCommentsByArticleId(Byte type, Integer articleId);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKeyWithBLOBs(Comment record);

    int updateByPrimaryKey(Comment record);
}