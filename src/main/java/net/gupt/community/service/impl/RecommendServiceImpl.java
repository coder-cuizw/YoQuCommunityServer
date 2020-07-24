package net.gupt.community.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.gupt.community.annotation.VisitorLimit;
import net.gupt.community.entity.CodeMsg;
import net.gupt.community.entity.Recommend;
import net.gupt.community.entity.Result;
import net.gupt.community.mapper.RecommendMapper;
import net.gupt.community.service.RecommendService;
import org.springframework.stereotype.Service;

/**
 * <h3>gupt-community</h3>
 * <p>投诉建议实现类</p>
 *
 * @author : Cui
 * @date : 2019-07-29 19:54
 **/
@Service
public class RecommendServiceImpl implements RecommendService {

    private final RecommendMapper recommendMapper;

    public RecommendServiceImpl(RecommendMapper recommendMapper) {
        this.recommendMapper = recommendMapper;
    }


    /**
     * 获取反馈
     *
     * @param pageNum  需要查询的页数
     * @param pageSize 每页的数量
     * @return 所有反馈信息
     */
    @Override
    public PageInfo<Recommend> getRecommends(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(recommendMapper.findAllRecommend());
    }

    /**
     * 发送反馈
     *
     * @param recommend 反馈信息
     * @return result
     * @author YG
     */
    @VisitorLimit
    @Override
    public Result<?> postRecommend(Recommend recommend) {
        int rows = recommendMapper.insert(recommend);
        return rows > 0 ?
                Result.success(CodeMsg.SUCCESS) :
                Result.error(CodeMsg.RECOMMEND_FAILED);
    }

    /**
     * 删除反馈
     *
     * @param id         主键ID
     * @param permission 权限
     * @return Result
     * @author YG
     */
    @Override
    public Result<?> deleteRecommend(Integer id, boolean permission) {
        if (permission) {
            int result = recommendMapper.deleteRecommend(id);
            if (result > 0) {
                return Result.success(CodeMsg.SUCCESS);
            }
        }
        return Result.error(CodeMsg.DELETE_FAILED);
    }
}
