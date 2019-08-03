package net.gupt.community.service;

import com.github.pagehelper.PageInfo;
import net.gupt.community.entity.Likes;

import java.util.List;

/**
 * 点赞服务层接口
 *
 * @author Cui
 */
public interface LikesService {

    PageInfo<Likes> getLikes(Integer articleId, Integer pageNum, Integer pageSize);

}
