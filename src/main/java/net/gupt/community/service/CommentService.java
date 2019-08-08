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

    PageInfo<Comment> getComments(Integer articleId, Integer pageNum, Integer pageSize);

    int postComment(Comment comment);

    int deleteComment(Integer articleId,Integer articleType);
}
