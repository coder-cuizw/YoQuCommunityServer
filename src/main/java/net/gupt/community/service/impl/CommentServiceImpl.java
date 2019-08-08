package net.gupt.community.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.gupt.community.entity.Comment;
import net.gupt.community.entity.PageInfoBean;
import net.gupt.community.mapper.CommentMapper;
import net.gupt.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * <h3>gupt-community</h3>
 * <p>评论业务层接口</p>
 *
 * @author : Cui
 * @date : 2019-07-30 18:34
 **/
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;

    public CommentServiceImpl(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Override
    public PageInfo<Comment> getComments(Integer articleId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(commentMapper.findCommentsByArticleId(articleId));
    }

    @Override
    public int postComment(Comment comment) {
        return commentMapper.insertByComment(comment);
    }

    @Override
    public int deleteComment(Integer articleId,Integer articleType) {
        return commentMapper.deleteByArticleId(articleId,articleType);
    }
}
