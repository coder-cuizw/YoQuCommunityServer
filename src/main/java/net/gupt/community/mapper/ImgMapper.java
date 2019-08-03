package net.gupt.community.mapper;

import net.gupt.community.entity.Img;

import java.util.List;

public interface ImgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Img record);

    int insertSelective(Img record);

    Img selectByPrimaryKey(Integer id);

    List<Img> findImgsByArticleId(Integer articleId);

    int updateByPrimaryKeySelective(Img record);

    int updateByPrimaryKey(Img record);
}