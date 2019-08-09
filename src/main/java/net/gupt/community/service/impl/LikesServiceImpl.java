package net.gupt.community.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.gupt.community.entity.Likes;
import net.gupt.community.mapper.LikesMapper;
import net.gupt.community.service.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h3>gupt-community</h3>
 * <p>点赞业务层实现类</p>
 *
 * @author : Cui
 * @date : 2019-07-30 19:26
 **/
@Service
public class LikesServiceImpl implements LikesService {

    private final LikesMapper likesMapper;

    public LikesServiceImpl(LikesMapper likesMapper) {
        this.likesMapper = likesMapper;
    }

    /**
     * 获取点赞列表
     * @param articleId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<Likes> getLikes(Integer articleId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(likesMapper.findLikesByArticleId(articleId));
    }

    /**
     * 发表点赞
     * @param uid
     * @param articleId
     * @return
     */
    @Override
    public int postLikes(Integer uid,Integer articleId) {
        return likesMapper.insertLikes(uid,articleId);
    }

    /**
     * 取消点赞
     * @param uid
     * @param articleId
     * @return
     */
    @Override
    public int deleteLikes(Integer uid, Integer articleId) {
        return likesMapper.deleteLikes(uid, articleId);
    }
}
