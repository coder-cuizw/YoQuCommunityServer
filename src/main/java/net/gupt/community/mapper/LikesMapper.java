package net.gupt.community.mapper;

import net.gupt.community.entity.Likes;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface LikesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Likes record);

    int insertSelective(Likes record);

    Likes selectByPrimaryKey(Integer id);

    List<Likes> findLikesByArticleId(Integer articleId);

    int updateByPrimaryKeySelective(Likes record);

    int updateByPrimaryKey(Likes record);
}