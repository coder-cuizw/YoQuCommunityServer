package net.gupt.community.mapper;

import net.gupt.community.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
@Component
public interface CommentMapper {
    int deleteByArticleId(Integer id);

    int insertByComment(Comment comment);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer id);

    List<Comment> findCommentsByArticleId(Integer articleId);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKeyWithBLOBs(Comment record);

    int updateByPrimaryKey(Comment record);
}