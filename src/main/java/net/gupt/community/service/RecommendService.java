package net.gupt.community.service;

import com.github.pagehelper.PageInfo;
import net.gupt.community.entity.Recommend;

/**
 * 投诉建议服务接口
 *
 * @author Cui
 */
public interface RecommendService {

    /**
     * 发布反馈建议
     *
     * @param recommend 信息
     * @return 结果状态
     */
    int postRecommend(Recommend recommend);

    /**
     * 通过参数获取分页
     *
     * @param pageNum  需要查询的页数
     * @param pageSize 每页的数量
     * @return 结果集
     */
    PageInfo<Recommend> getRecommends(Integer pageNum, Integer pageSize);

    /**
     * 删除反馈
     *
     * @param id 主键ID
     * @return int
     */
    int deleteRecommend(Integer id);
}
