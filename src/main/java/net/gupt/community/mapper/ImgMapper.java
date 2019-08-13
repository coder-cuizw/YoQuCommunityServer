package net.gupt.community.mapper;

import net.gupt.community.entity.Img;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ImgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Img record);

    int insertSelective(Img record);

    Img selectByPrimaryKey(Integer id);

    List<Img> findImgsByArticleId(Integer articleId,Byte articleType);

    int updateByPrimaryKeySelective(Img record);

    int updateByPrimaryKey(Img record);
}