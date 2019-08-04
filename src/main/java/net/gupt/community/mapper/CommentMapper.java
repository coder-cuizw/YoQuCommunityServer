package net.gupt.community.mapper;

import net.gupt.community.entity.Comment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer id);

    List<Comment> findCommentsByArticleId(Integer articleId);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKeyWithBLOBs(Comment record);

    int updateByPrimaryKey(Comment record);
}