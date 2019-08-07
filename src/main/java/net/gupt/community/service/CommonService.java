package net.gupt.community.service;

import com.github.pagehelper.PageInfo;
import net.gupt.community.entity.Common;

/**
 * 通用帖子业务层
 *
 * @author Cui
 */
public interface CommonService {

    PageInfo<Common> getArticles(Integer postType, Integer pageNum, Integer pageSize);

    int postArticle(Common common);

}
