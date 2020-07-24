package net.gupt.community.service;

import com.github.pagehelper.PageInfo;
import net.gupt.community.entity.Comment;
import net.gupt.community.entity.Result;
import net.gupt.community.entity.Student;

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
     *
     * @param articleId 文章ID
     * @param pageNum   文章页码
     * @param pageSize  文章页面大小
     * @param type      文章类型
     * @return PageInfo
     */
    PageInfo<Comment> getComments(Byte type, Integer articleId, Integer pageNum, Integer pageSize);

    /**
     * 发表评论
     *
     * @param comment 评论实体对象
     * @return int
     */
    Result<?> postComment(Comment comment);


    /**
     * 删除评论
     *
     * @param id         文章ID
     * @param commentUid 发布评论的学号
     * @param student    学生对象
     * @return int
     */
    Result<?> deleteByPrimaryId(Integer id, Integer commentUid, Student student);
}
