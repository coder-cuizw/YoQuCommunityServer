package net.gupt.community.service.impl;

import net.gupt.community.annotation.VisitorLimit;
import net.gupt.community.entity.CodeMsg;
import net.gupt.community.entity.Img;
import net.gupt.community.entity.Result;
import net.gupt.community.mapper.ImgMapper;
import net.gupt.community.service.ImgService;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Img> getImgs(Integer articleId, Byte articleType) {
        return imgMapper.findImgsByArticleId(articleId, articleType);
    }

    @VisitorLimit
    @Override
    public Result<?> postImg(Img img) {
        int rows = imgMapper.insert(img);
        return rows > 0 ?
                Result.success(CodeMsg.SUCCESS) : Result.success(CodeMsg.POST_FAILED);
    }
}
