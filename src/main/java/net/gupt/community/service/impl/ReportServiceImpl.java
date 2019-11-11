package net.gupt.community.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.gupt.community.entity.CodeMsg;
import net.gupt.community.entity.Report;
import net.gupt.community.entity.Result;
import net.gupt.community.entity.Student;
import net.gupt.community.mapper.CommonMapper;
import net.gupt.community.mapper.FoundMapper;
import net.gupt.community.mapper.ReportMapper;
import net.gupt.community.service.ReportService;
import net.gupt.community.util.ArticleUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h3>gupt-community</h3>
 * <p>举报服务实现类</p>
 *
 * @author : Cui
 * @date : 2019-07-30 02:33
 **/
@Service
public class ReportServiceImpl implements ReportService {

    private final ReportMapper reportMapper;
    private final CommonMapper commonMapper;
    private final FoundMapper foundMapper;

    public ReportServiceImpl(ReportMapper reportMapper, CommonMapper commonMapper, FoundMapper foundMapper) {
        this.reportMapper = reportMapper;
        this.commonMapper = commonMapper;
        this.foundMapper = foundMapper;
    }

    @Override
    public PageInfo<Report> getReports(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Report> reports = reportMapper.findAllReport();
        return new PageInfo<>(reports);
    }

    /**
     * 发送举报
     *
     * @param report 举报信息
     * @return Result
     */
    @Override
    public Result postReport(Report report, Student student) {
        Map<String, Object> map = new HashMap<>(10);
        map.put("commonMapper", commonMapper);
        map.put("foundMapper", foundMapper);
        Integer articleId = report.getArticleId();
        byte articleType = report.getArticleType();
        boolean result = ArticleUtil.isExist(articleId, articleType, map);
        if (result) {
            report.setUid(student.getUid());
            int rows = reportMapper.insert(report);
            return rows > 0 ? Result.success(CodeMsg.SUCCESS) : Result.error(CodeMsg.REPORT_FAILED);
        }
        return Result.error(CodeMsg.MISSING_RECORD);
    }

    /**
     * 删除举报
     *
     * @param id 主键id
     * @return Result
     */
    @Override
    public Result deleteReport(Integer id, Student student) {
        boolean permission = student.getPermission();
        if (permission) {
            int result = reportMapper.deleteReport(id);
            if (result > 0) {
                return Result.success(CodeMsg.SUCCESS);
            }
        }
        return Result.error(CodeMsg.DELETE_FAILED);
    }
}
