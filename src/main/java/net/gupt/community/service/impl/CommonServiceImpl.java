package net.gupt.community.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import net.gupt.community.entity.Common;
import net.gupt.community.entity.Img;
import net.gupt.community.exception.GlobalException;
import net.gupt.community.mapper.CommonMapper;
import net.gupt.community.mapper.ImgMapper;
import net.gupt.community.service.CommonService;
import net.gupt.community.vo.CommonVo;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

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


    public CommonServiceImpl(CommonMapper commonMapper, ImgMapper imgMapper) {
        this.commonMapper = commonMapper;
        this.imgMapper = imgMapper;
    }

    @Override
    public PageInfo<CommonVo> getArticles(Byte postType, Integer pageNum, Integer pageSize, Integer uid, Integer id, Boolean isTop, Boolean isSearch, String searchContent) {
        PageHelper.startPage(pageNum, pageSize);
        if (searchContent != null) {
            String content;
            try {
                content = URLDecoder.decode(searchContent, StandardCharsets.UTF_8.toString());
            } catch (UnsupportedEncodingException e) {
                throw new GlobalException();
            }
            return new PageInfo<>(commonMapper.findAllCommonsWithVO(postType, uid, id, isTop, isSearch, content));
        }
        return new PageInfo<>(commonMapper.findAllCommonsWithVO(postType, uid, id, isTop, isSearch, null));

    }

    @Override
    public int postArticle(Common common) {
        return commonMapper.insert(common);
    }


    @Override
    public int deleteArticle(Integer articleType, Integer id) {
        return commonMapper.deleteArticleByIdAndType(articleType, id);
    }

    @Override
    public int findCommonArticleById(Integer articleId) {
        return commonMapper.findCommonArticleById(articleId);
    }


    /**
     * 插入图片数据
     *
     * @param img 图片对象
     * @return int
     */
    @Override
    public int postImg(Img img) {
        return imgMapper.insert(img);
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
