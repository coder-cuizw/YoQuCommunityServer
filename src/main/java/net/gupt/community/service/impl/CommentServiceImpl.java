package net.gupt.community.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.gupt.community.entity.Comment;
import net.gupt.community.mapper.CommentMapper;
import net.gupt.community.service.CommentService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * <h3>gupt-community</h3>
 * <p>评论业务层接口</p>
 *
 * @author : Cui
 * @date : 2019-07-30 18:34
 **/
@CacheConfig(cacheNames = {"comment","commonArticles","foundArticles"})
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;

    public CommentServiceImpl(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    /**
     * 获取评论列表
     *
     * @param articleId 文章ID
     * @param pageNum   页码
     * @param pageSize  页面大小
     * @return PageInfo<Comment>
     */
    @Cacheable
    @Override
    public PageInfo<Comment> getComments(Byte type, Integer articleId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(commentMapper.findCommentsByArticleId(type, articleId));
    }

    /**
     * 发表评论
     *
     * @param comment 评论对象
     * @return int
     */
    @CacheEvict(allEntries = true)
    @Override
    public int postComment(Comment comment) {
        return commentMapper.insertByComment(comment);
    }

    /**
     * 删除评论
     * @param id
     * @return int
     */
    @CacheEvict(allEntries = true)
    @Override
    public int deleteByPrimaryId(Integer id) {
        return commentMapper.deleteByPrimaryId(id);
    }
}
