package net.gupt.community.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import net.gupt.community.entity.*;
import net.gupt.community.exception.GlobalException;
import net.gupt.community.mapper.CommonMapper;
import net.gupt.community.mapper.ImgMapper;
import net.gupt.community.service.CommonService;
import net.gupt.community.util.ArticleUtil;
import net.gupt.community.util.QiniuUtil;
import net.gupt.community.vo.CommonVo;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

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
    private final Qiniu qiniu;


    public CommonServiceImpl(CommonMapper commonMapper, ImgMapper imgMapper, Qiniu qiniu) {
        this.commonMapper = commonMapper;
        this.imgMapper = imgMapper;
        this.qiniu = qiniu;
    }

    @Override
    public PageInfo<CommonVo> getArticles(Byte postType, Integer pageNum, Integer pageSize, Integer uid, Integer id, Boolean isTop, Boolean isSearch, String searchContent) {
        PageHelper.startPage(pageNum, pageSize);
        if (searchContent != null) {
            String content;
            try {
                content = ArticleUtil.replaceStr(URLDecoder.decode(searchContent, StandardCharsets.UTF_8.toString()));
            } catch (UnsupportedEncodingException e) {
                throw new GlobalException();
            }
            return new PageInfo<>(commonMapper.findAllCommonsWithVO(postType, uid, id, isTop, isSearch, content));
        }
        return new PageInfo<>(commonMapper.findAllCommonsWithVO(postType, uid, id, isTop, isSearch, null));
    }

    /**
     * 发送帖子
     *
     * @param commonVo <br/>
     * @return Result
     */
    @Override
    public Result postArticle(CommonVo commonVo) {
        int result = commonMapper.insert(commonVo);
        if (result > 0) {
            List<Img> imgList = commonVo.getImg();
            if (imgList != null && !imgList.isEmpty()) {
                Integer id = commonVo.getId();
                Byte postType = commonVo.getPostType();
                imgList.stream().filter(img -> !img.getImgUrl().trim().isEmpty()).forEach(img -> {
                    img.setArticleId(id).setArticleType(postType);
                    imgMapper.insert(img);
                });
            }
            return Result.success(CodeMsg.SUCCESS, commonVo.getId());
        }
        return Result.error(CodeMsg.FAILED);
    }


    /**
     * 删除贴子相关数据
     *
     * @param articleType 帖子类型
     * @param id          文章ID
     * @param uid         学号
     * @return Result
     */
    @Override
    public Result deleteArticle(Byte articleType, Integer id, Integer uid, Student student) {
        boolean isMe = uid.equals(student.getUid());
        boolean permission = student.getPermission();
        List<Img> imgList = imgMapper.findImgsByArticleId(id, articleType);
        if (isMe || permission) {
            int rows = commonMapper.deleteArticleByIdAndType(articleType, id, uid);
            boolean delResult = QiniuUtil.delete(qiniu.getAccessKey(), qiniu.getSecretKey(), qiniu.getBucket(), rows, imgList);
            if (rows > 0 || delResult) {
                return Result.success(CodeMsg.SUCCESS);
            }
        }
        return Result.error(CodeMsg.DELETE_FAILED);
    }

    @Override
    public int findCommonArticleById(Integer articleId) {
        return commonMapper.findCommonArticleById(articleId);
    }

    /**
     * 设置置顶帖子
     *
     * @param common 图片对象
     * @return int
     */
    @Override
    public Result setTop(Common common) {
        int result = commonMapper.setTop(common);
        return result == 0 ? Result.error(CodeMsg.FAILED) : Result.success(CodeMsg.SUCCESS);
    }
}
