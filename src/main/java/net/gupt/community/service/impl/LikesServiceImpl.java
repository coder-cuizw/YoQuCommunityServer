package net.gupt.community.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.gupt.community.entity.CodeMsg;
import net.gupt.community.entity.Likes;
import net.gupt.community.entity.Result;
import net.gupt.community.mapper.CommonMapper;
import net.gupt.community.mapper.FoundMapper;
import net.gupt.community.mapper.LikesMapper;
import net.gupt.community.service.LikesService;
import net.gupt.community.util.ArticleUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <h3>gupt-community</h3>
 * <p>点赞业务层实现类</p>
 *
 * @author : Cui
 * @date : 2019-07-30 19:26
 **/
@Service
@Slf4j
public class LikesServiceImpl implements LikesService {

    private final LikesMapper likesMapper;
    private final FoundMapper foundMapper;
    private final CommonMapper commonMapper;
    private final String stu = "Student";

    public LikesServiceImpl(LikesMapper likesMapper, FoundMapper foundMapper, CommonMapper commonMapper) {
        this.likesMapper = likesMapper;
        this.foundMapper = foundMapper;
        this.commonMapper = commonMapper;
    }

    /**
     * 获取点赞数量
     *
     * @param articleId   文章ID
     * @param articleType 文章类型
     * @return Likes
     */
    @Override
    public Result getLikes(Integer articleId, Byte articleType) {
        Likes likes = likesMapper.findLikes(articleId, articleType);
        return likes.getLoveNum() > 0 ?
                Result.success(CodeMsg.SUCCESS, likes.getLoveNum()) :
                Result.error(CodeMsg.MISSING_RECORD, likes.getLoveNum());
    }

    /**
     * 获取浏览量
     *
     * @param articleId   文章ID
     * @param articleType 文章类型
     * @param info        区分重装方法
     * @return Likes
     */
    @Override
    public Result getLikes(Integer articleId, Byte articleType, String info) {
        Likes view = likesMapper.findView(articleId, articleType);
        return view.getLoveNum() > 0 ?
                Result.success(CodeMsg.SUCCESS, view.getLoveNum()) :
                Result.error(CodeMsg.MISSING_RECORD, view.getLoveNum());
    }

    /**
     * 检验是否存在点赞记录
     *
     * @param articleId   <br/>
     * @param articleType <br/>
     * @param uid         <br/>
     * @return int
     * @author YG
     */
    @Override
    public Result findIsLikes(Integer articleId, Byte articleType, Integer uid) {
        Likes isLikes = likesMapper.findIsLikes(articleId, articleType, uid);
        return isLikes != null ?
                Result.success(CodeMsg.SUCCESS, true) :
                Result.error(CodeMsg.MISSING_RECORD, false);
    }

    /**
     * 检验是否存在浏览记录
     *
     * @param articleId   <br/>
     * @param articleType <br/>
     * @param uid         <br/>
     * @param info        标识
     * @return int
     * @author YG
     */

    @Override
    public Result findIsLikes(Integer articleId, Byte articleType, Integer uid, String info) {
        Likes isViews = likesMapper.findIsViews(articleId, articleType, uid, info);
        return isViews != null ?
                Result.success(CodeMsg.SUCCESS, true) : Result.error(CodeMsg.MISSING_RECORD, false);
    }

    /**
     * 发表点赞
     *
     * @param likes Likes对象
     * @return Result
     */

    @Override
    public Result postLikes(Likes likes) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("commonMapper", commonMapper);
        map.put("foundMapper", foundMapper);
        Integer articleId = likes.getArticleId();
        byte articleType = likes.getArticleType();
        boolean result = ArticleUtil.isExist(articleId, articleType, map);
        if (result) {
            int executeResult = likesMapper.insertLikes(likes);
            return executeResult > 0 ? Result.success(CodeMsg.SUCCESS) : Result.error(CodeMsg.FAILED);
        }
        return Result.error(CodeMsg.MISSING_RECORD);
    }

    /**
     * 删除点赞
     *
     * @param articleType 文章类型
     * @param articleId   文章ID
     * @return int
     */
    @Override
    public Result deleteLikes(Integer articleId, Byte articleType, Integer uid) {
        int rows = likesMapper.deleteLikes(articleId, articleType, uid);
        return rows > 0 ?
                Result.success(CodeMsg.SUCCESS) : Result.error(CodeMsg.MISSING_RECORD, false);

    }

}
