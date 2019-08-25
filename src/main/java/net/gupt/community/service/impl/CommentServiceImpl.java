package net.gupt.community.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.gupt.community.entity.Comment;
import net.gupt.community.mapper.CommentMapper;
import net.gupt.community.service.CommentService;
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

    /**
     * 获取评论列表
     *
     * @param articleId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<Comment> getComments(Byte type, Integer articleId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(commentMapper.findCommentsByArticleId(type, articleId));
    }

    /**
     * 发表评论
     *
     * @param comment
     * @return
     */
    @Override
    public int postComment(Comment comment) {
        return commentMapper.insertByComment(comment);
    }

    /**
     * 删除评论
     *
     * @param articleId
     * @param articleType
     * @return
     */
    @Override
    public int deleteComment(Integer articleId, Integer articleType) {
        return commentMapper.deleteByArticleId(articleId, articleType);
    }

    @Override
    public int deleteByPrimaryId(Integer id) {
        return commentMapper.deleteByPrimaryId(id);
    }
}
