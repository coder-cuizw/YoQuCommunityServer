package net.gupt.community.service.impl;

import net.gupt.community.entity.Likes;
import net.gupt.community.mapper.LikesMapper;
import net.gupt.community.service.LikesService;
import org.springframework.stereotype.Service;

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
     *
     * @param articleId
     * @param articleType
     * @return
     */


    @Override
    public Likes getLikes(Integer articleId, Byte articleType) {
        return likesMapper.findLikesWithView(articleId, articleType);
    }

    /**
     * 发表点赞
     *
     * @param likes
     * @return
     */
    @Override
    public int postLikes(Likes likes) {
        return likesMapper.insertLikes(likes);
    }

    /**
     * 删除点赞
     *
     * @param articleType
     * @param articleId
     * @return
     */
    @Override
    public int deleteLikes(Integer articleId,Byte articleType,String openId) {
        return likesMapper.deleteLikes(articleId,articleType,openId);
    }
}
