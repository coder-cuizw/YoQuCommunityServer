package net.gupt.community.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.gupt.community.entity.CodeMsg;
import net.gupt.community.entity.Comment;
import net.gupt.community.entity.Result;
import net.gupt.community.entity.Student;
import net.gupt.community.mapper.CommentMapper;
import net.gupt.community.mapper.CommonMapper;
import net.gupt.community.mapper.FoundMapper;
import net.gupt.community.service.CommentService;
import net.gupt.community.util.ArticleUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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
    private final FoundMapper foundMapper;
    private final CommonMapper commonMapper;

    public CommentServiceImpl(CommentMapper commentMapper, FoundMapper foundMapper, CommonMapper commonMapper) {
        this.commentMapper = commentMapper;
        this.foundMapper = foundMapper;
        this.commonMapper = commonMapper;
    }

    /**
     * 获取评论列表
     *
     * @param articleId 文章ID
     * @param pageNum   页码
     * @param pageSize  页面大小
     * @return PageInfo<Comment>
     */
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
    @Override
    public Result postComment(Comment comment) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("commonMapper", commonMapper);
        map.put("foundMapper", foundMapper);
        Integer articleId = comment.getArticleId();
        Byte articleType = comment.getType();
        boolean result = ArticleUtil.isExist(articleId, articleType, map);
        if (result) {
            int rows = commentMapper.insertByComment(comment);
            return rows > 0 ? Result.success(CodeMsg.SUCCESS) : Result.error(CodeMsg.FAILED);
        }
        return Result.error(CodeMsg.MISSING_RECORD);
    }


    /**
     * 删除评论
     *
     * @param id 主键id
     * @return int
     */
    @Override
    public Result deleteByPrimaryId(Integer id, Integer commentUid, Student student) {
        boolean isMe = commentUid.equals(student.getUid());
        boolean permission = student.getPermission();
        if (isMe || permission) {
            int rows = commentMapper.deleteByPrimaryId(id, commentUid);
            if (rows > 0) {
                return Result.success(CodeMsg.SUCCESS);
            }
        }
        return Result.error(CodeMsg.DELETE_FAILED);
    }
}
