package net.gupt.community.service;

import com.github.pagehelper.PageInfo;
import net.gupt.community.entity.Recommend;
import net.gupt.community.entity.Result;

/**
 * 投诉建议服务接口
 *
 * @author Cui
 */
public interface RecommendService {

    /**
     * 通过参数获取分页
     *
     * @param pageNum  需要查询的页数
     * @param pageSize 每页的数量
     * @return 结果集
     */
    PageInfo<Recommend> getRecommends(Integer pageNum, Integer pageSize);

    /**
     * 发布反馈建议
     *
     * @param recommend 信息
     * @return 结果状态
     */
    Result<?> postRecommend(Recommend recommend);

    /**
     * 删除反馈
     *
     * @param id         主键ID
     * @param permission 权限
     * @return int
     */
    Result<?> deleteRecommend(Integer id, boolean permission);
}
