package net.gupt.community.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.gupt.community.entity.Img;
import net.gupt.community.entity.Likes;
import net.gupt.community.mapper.ImgMapper;
import net.gupt.community.service.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * <h3>gupt-community</h3>
 * <p>获取图片业务层实现类</p>
 *
 * @author : Cui
 * @date : 2019-07-30 20:44
 **/
@Service
public class ImgServiceImpl implements ImgService {

    private final ImgMapper imgMapper;

    public ImgServiceImpl(ImgMapper imgMapper) {
        this.imgMapper = imgMapper;
    }

    @Override
    public PageInfo<Img> getImgs(Integer articleId,Byte articleType, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(imgMapper.findImgsByArticleId(articleId,articleType));
    }

    @Override
    public int postImg(Img img) {
        return imgMapper.insertSelective(img);
    }
}
