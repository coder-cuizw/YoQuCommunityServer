package net.gupt.community.mapper;

import net.gupt.community.VO.FoundQueryVO;
import net.gupt.community.entity.Found;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface FoundMapper {
    /**
     * 通过主键删除文章
     *
     * @param id <br/>
     * @return int
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 发表文章 不校验空值
     *
     * @param record <br/>
     * @return int
     */
    int insert(Found record);

    /**
     * 发表文章，检验空值
     *
     * @param record <br/>
     * @return int
     */
    int insertSelective(Found record);

    /**
     * 通过主键查询
     *
     * @param id
     * @return
     */
    Found selectByPrimaryKey(Integer id);

    /**
     * 动态查询所有
     * @return List
     */
    List<Found> findAllFound(FoundQueryVO query);

    /**
     * 更新失物状态
     *
     * @param record <br/>
     * @return int
     */
    int updateFoundStatusById(Found record);

    int updateByPrimaryKeySelective(Found record);

    int updateByPrimaryKeyWithBLOBs(Found record);

    int updateByPrimaryKey(Found record);
}