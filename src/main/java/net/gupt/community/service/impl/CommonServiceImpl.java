package net.gupt.community.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import net.gupt.community.entity.Common;
import net.gupt.community.entity.Img;
import net.gupt.community.entity.Likes;
import net.gupt.community.mapper.CommonMapper;
import net.gupt.community.mapper.ImgMapper;
import net.gupt.community.service.CommonService;
import org.springframework.stereotype.Service;

/**
 * <h3>gupt-community</h3>
 * <p>通用帖子业务层实现类</p>
 *
 * @author : Cui
 * @date : 2019-07-30 16:50
 **/
@Slf4j
@Service
public class CommonServiceImpl implements CommonService {

    private final CommonMapper commonMapper;
    private final ImgMapper imgMapper;
    private final LikesServiceImpl likesService;

    public CommonServiceImpl(CommonMapper commonMapper, ImgMapper imgMapper, LikesServiceImpl likesService) {
        this.commonMapper = commonMapper;
        this.imgMapper = imgMapper;
        this.likesService = likesService;
    }

    @Override
    public PageInfo<Common> getArticles(Byte postType, Integer pageNum, Integer pageSize, Integer uid, Integer id, Boolean isTop) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(commonMapper.findAllCommons(postType, uid, id, isTop));
    }


    @Override
    public int postArticle(Common common) {
        return commonMapper.insert(common);
    }

    @Override
    public int deleteArticle(Integer articleType, Integer id) {
        return commonMapper.deleteArticleByIdAndType(articleType, id);
    }

    /**
     * 获取单条帖子的所有信息
     *
     * @param articleId 文章ID
     * @param postType  文章类型
     * @return Common
     */
    @Override
    public Common articleInfo(Integer articleId, Byte postType, Common common, Likes likes) {
        //如果list为空跑系统异常
        common = commonMapper.findAllCommons(postType, null, articleId, null).get(0);
        common.setImg(imgMapper.findImgsByArticleId(articleId, postType));
        common.setLikes(likesService.findLovesAndViews(articleId, postType, likes));
        return common;
    }


    /**
     * 插入图片数据
     *
     * @param img 图片对象
     * @return int
     */
    @Override
    public int postImg(Img img) {
        return commonMapper.insertImg(img);
    }

    /**
     * 设置置顶帖子
     *
     * @param common 图片对象
     * @return int
     */
    @Override
    public int setTop(Common common) {
        return commonMapper.setTop(common);
    }
}
