package net.gupt.community.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import net.gupt.community.entity.Common;
import net.gupt.community.entity.Img;
import net.gupt.community.mapper.CommonMapper;
import net.gupt.community.service.CommonService;
import net.gupt.community.vo.CommonVo;
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


    public CommonServiceImpl(CommonMapper commonMapper) {
        this.commonMapper = commonMapper;
    }

    @Override
    public PageInfo<CommonVo> getArticles(Byte postType, Integer pageNum, Integer pageSize, Integer uid, Integer id, Boolean isTop, Boolean isSearch, String searchContent) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(commonMapper.findAllCommonsWithVO(postType, uid, id, isTop, isSearch, searchContent));

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
