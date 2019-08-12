package net.gupt.community.service;

import com.github.pagehelper.PageInfo;
import net.gupt.community.entity.Comment;

/**
 * <h3>gupt-community</h3>
 * <p>评论业务层接口类</p>
 *
 * @author : Cui
 * @date : 2019-07-30 18:34
 **/
public interface CommentService {
    /**
     * 获取评论列表
     * @param articleId
     * @param pageNum
     * @param pageSize
     * @return PageInfo
     */
    PageInfo<Comment> getComments(Byte type, Integer articleId, Integer pageNum, Integer pageSize);

    /**
     * 发表评论
     * @param comment
     * @return int
     */
    int postComment(Comment comment);

    /**
     * 删除评论
     * @param articleId
     * @param articleType
     * @return int
     */
    int deleteComment(Integer articleId,Integer articleType);
}
