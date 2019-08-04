package net.gupt.community.mapper;

import net.gupt.community.entity.Common;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Cui
 */
@Component
public interface CommonMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Common record);

    int insertSelective(Common record);

    Common selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Common record);

    int updateByPrimaryKeyWithBLOBs(Common record);

    int updateByPrimaryKey(Common record);

    List<Common> findAllCommons(Integer postType);
}