package net.gupt.community.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.gupt.community.entity.Report;
import net.gupt.community.mapper.ReportMapper;
import net.gupt.community.service.ReportService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h3>gupt-community</h3>
 * <p>举报服务实现类</p>
 *
 * @author : Cui
 * @date : 2019-07-30 02:33
 **/
@CacheConfig(cacheNames = {"report"})
@Service
public class ReportServiceImpl implements ReportService {

    private final ReportMapper reportMapper;

    public ReportServiceImpl(ReportMapper reportMapper) {
        this.reportMapper = reportMapper;
    }

    @CacheEvict(allEntries = true)
    @Override
    public int postReport(Report report) {
        return reportMapper.insert(report);
    }

    @Cacheable
    @Override
    public PageInfo<Report> getReports(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Report> reports = reportMapper.findAllReport();
        return new PageInfo<>(reports);
    }
}
