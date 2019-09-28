package net.gupt.community.service;

import com.github.pagehelper.PageInfo;
import net.gupt.community.entity.Common;
import net.gupt.community.entity.Img;

/**
 * 通用帖子业务层
 *
 * @author Cui
 */
public interface CommonService {

    PageInfo<Common> getArticles(Byte postType, Integer pageNum, Integer pageSize, String openId,Integer id);

    int postArticle(Common common);

    int deleteArticle(Integer articleType, Integer id);

    /**
     * 发送图片
     * @param img
     * @return
     */
    int postImg(Img img);
}
