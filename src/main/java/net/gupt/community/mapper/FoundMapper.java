package net.gupt.community.mapper;

import net.gupt.community.entity.Found;

import java.util.List;

public interface FoundMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Found record);

    int insertSelective(Found record);

    Found selectByPrimaryKey(Integer id);

    List<Found> findAllFound();

    int updateByPrimaryKeySelective(Found record);

    int updateByPrimaryKeyWithBLOBs(Found record);

    int updateByPrimaryKey(Found record);
}