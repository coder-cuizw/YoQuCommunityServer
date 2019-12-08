package net.gupt.community.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.gupt.community.annotation.VisitorLimit;
import net.gupt.community.controller.websocket.WebSocketMsgController;
import net.gupt.community.entity.*;
import net.gupt.community.mapper.FoundMapper;
import net.gupt.community.mapper.ImgMapper;
import net.gupt.community.service.FoundService;
import net.gupt.community.util.ArticleUtil;
import net.gupt.community.util.QiniuUtil;
import net.gupt.community.vo.FoundVo;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;


/**
 * <h3>gupt-community</h3>
 * <p>失物找回接口实现类</p>
 *
 * @author : Cui
 * @date : 2019-07-30 21:03
 **/
@Service
public class FoundServiceImpl implements FoundService {

    private final FoundMapper foundMapper;
    private final ImgMapper imgMapper;
    private final Qiniu qiniu;


    public FoundServiceImpl(FoundMapper foundMapper, ImgMapper imgMapper, Qiniu qiniu) {
        this.foundMapper = foundMapper;
        this.imgMapper = imgMapper;
        this.qiniu = qiniu;
    }

    /**
     * 查询所有失物信息
     *
     * @param pageNum  <br/>
     * @param pageSize <br/>
     * @param isSearch <br/>
     * @return PageInfo<Found>
     */
    @Override
    public PageInfo<Found> getFounds(Integer pageNum, Integer pageSize, Integer id, Boolean articleState, Boolean isTop, Integer uid, Boolean isSearch, String searchContent) {
        PageHelper.startPage(pageNum, pageSize);
        if (searchContent != null) {
            String content;
            try {
                content = ArticleUtil.replaceStr(URLDecoder.decode(searchContent, StandardCharsets.UTF_8.toString()));
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
            return new PageInfo<>(foundMapper.findAllFound(id, articleState, isTop, uid, content, isSearch));
        }
        return new PageInfo<>(foundMapper.findAllFound(id, articleState, isTop, uid, null, isSearch));
    }

    /**
     * 发送失物接口
     *
     * @param foundVo <br/>
     * @return int
     */
    @VisitorLimit
    @Override
    public Result postFound(FoundVo foundVo) {
        int rows = foundMapper.insertSelective(foundVo);
        if (rows > 0) {
            List<Img> imgList = foundVo.getImg();
            if (imgList != null && !imgList.isEmpty()) {
                Integer articleId = foundVo.getId();
                imgList.stream().filter(img -> !img.getImgUrl().trim().isEmpty()).forEach(img -> {
                    img.setArticleId(articleId).setArticleType((byte) 2);
                    imgMapper.insert(img);
                });
            }
            WebSocketMsgController.globalNotification(Result.success(CodeMsg.NEW_NOTIFY, 2));
            return Result.success(CodeMsg.SUCCESS, foundVo.getId());
        }
        return Result.error(CodeMsg.POST_FAILED);
    }

    /**
     * 更新失物状态接口
     *
     * @param found found对象l
     * @return int
     */
    @VisitorLimit
    @Override
    public Result updateFoundStatus(Found found) {
        int rows = foundMapper.updateFoundStatusById(found);
        return rows > 0 ? Result.success(CodeMsg.SUCCESS) : Result.error(CodeMsg.UPDATE_FAILED);
    }

    /**
     * 删除失物有关的所有信息
     *
     * @param id <br/>
     * @return int
     */
    @VisitorLimit
    @Override
    public Result deleteFoundInfo(Integer id, Integer uid, Student student) {
        boolean isMe = uid.equals(student.getUid());
        boolean permission = student.getPermission();
        List<Img> imgList = imgMapper.findImgsByArticleId(id, (byte) 2);
        if (isMe || permission) {
            int rows = foundMapper.deleteByPrimaryKey(id, uid);
            boolean delResult = QiniuUtil.delete
                    (qiniu.getAccessKey(), qiniu.getSecretKey(), qiniu.getBucket(), rows, imgList);
            if (rows > 0 || delResult) {
                return Result.success(CodeMsg.SUCCESS);
            }
        }
        return Result.error(CodeMsg.DELETE_FAILED);
    }


    @Override
    public int findFoundArticleById(Integer articleId) {
        return foundMapper.findFoundArticleById(articleId);
    }


}
