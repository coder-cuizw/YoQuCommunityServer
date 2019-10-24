package net.gupt.community.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.gupt.community.entity.Recommend;
import net.gupt.community.mapper.RecommendMapper;
import net.gupt.community.service.RecommendService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

    @Override
    public int postRecommend(Recommend recommend) {
        return recommendMapper.insert(recommend);
    }

    @Override
    public PageInfo<Recommend> getRecommends(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(recommendMapper.findAllRecommend());
    }

    @Override
    public int deleteRecommend(Integer id) {
        return recommendMapper.deleteRecommend(id);
    }
}
