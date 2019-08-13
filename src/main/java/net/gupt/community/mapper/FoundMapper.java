package net.gupt.community.mapper;

import net.gupt.community.vo.FoundQueryVo;
import net.gupt.community.entity.Found;
import org.springframework.stereotype.Component;

import java.util.List;

@Component

/**
 * Description  失物找回Mapper<br/>
 * @author Administrator<br />
 * @date 2019/8/7 16:13<br/>
 */
public interface FoundMapper {
    /**
     * 通过主键删除所有有关失物的信息
     *
     * @param id <br/>
     * @return int
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 发表文章，检验空值
     *
     * @param record <br/>
     * @return int
     */
    int insertSelective(Found record);

    /**
     * 动态查询所有
     *
     * @param query 查询条件的封装对象
     * @return List
     */
    List<Found> findAllFound(FoundQueryVo query);

    /**
     * 更新失物状态
     *
     * @param record <br/>
     * @return int
     */
    int updateFoundStatusById(Found record);

    /**
     * 通过主键更新失物信息检验NULL
     *
     * @param record <br/>
     * @return int
     */
    int updateByPrimaryKeySelective(Found record);

}