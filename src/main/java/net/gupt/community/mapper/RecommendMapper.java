package net.gupt.community.mapper;

import net.gupt.community.entity.Recommend;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 反馈建议数据层
 *
 * @author Cui
 */
@Component
public interface RecommendMapper {

    /**
     * 发送反馈信息
     *
     * @param record 反馈激励
     * @return int
     */
    int insert(Recommend record);

    /**
     * 获取反馈信息
     *
     * @return List
     */
    List<Recommend> findAllRecommend();

    /**
     * 获取反馈建议
     *
     * @param id 主键Id
     * @return int
     */
    int deleteRecommend(Integer id);
}