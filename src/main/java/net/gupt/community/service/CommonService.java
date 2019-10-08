package net.gupt.community.service;

import com.github.pagehelper.PageInfo;
import net.gupt.community.entity.Common;
import net.gupt.community.entity.Img;
import net.gupt.community.entity.Likes;

/**
 * 通用帖子业务层
 *
 * @author Cui
 */
public interface CommonService {


    /**
     * 发送图片
     *
     * @param img 图片
     * @return int
     */
    int postImg(Img img);

    /**
     * 设置指定
     *
     * @param common 通用帖子对象
     * @return int
     */
    int setTop(Common common);

    /**
     * 获取帖子信息
     *
     * @param postType 文章类型
     * @param pageNum  页码
     * @param pageSize 页面大小
     * @param uid      学 号
     * @param id       帖子id
     * @param isTop    是否指定
     * @return  PageInfo<Common>
     */
    PageInfo<Common> getArticles(Byte postType, Integer pageNum, Integer pageSize, Integer uid, Integer id, Boolean isTop);

    /**
     * 发送贴字
     *
     * @param common 帖子类型
     * @return int
     */
    int postArticle(Common common);

    /**
     * 删除帖子
     *
     * @param articleType 帖子类型
     * @param id          文章ID
     * @return int
     */
    int deleteArticle(Integer articleType, Integer id);

    /**
     * 获取帖子类型
     * @param articleId 文章ID
     * @param postType 文章类型
     * @return Common
     */
    Common ArticleInfo(Integer articleId, Byte postType, Common common, Likes likes);
}
