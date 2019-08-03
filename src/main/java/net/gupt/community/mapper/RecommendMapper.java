package net.gupt.community.mapper;

import net.gupt.community.entity.Recommend;

import java.util.List;

/**
 * 反馈建议数据层
 *
 * @author Cui
 */
public interface RecommendMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Recommend record);

    int insertSelective(Recommend record);

    Recommend selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Recommend record);

    int updateByPrimaryKeyWithBLOBs(Recommend record);

    int updateByPrimaryKey(Recommend record);

    List<Recommend> findAllRecommend();
}