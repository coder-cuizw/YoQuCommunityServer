package net.gupt.community.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import net.gupt.community.entity.Report;

/**
 * 举报服务接口
 *
 * @author Cui
 */
public interface ReportService {

    /**
     * 发送举报
     *
     * @param report 举报信息
     * @return 返回执行状态码
     */
    int postReport(Report report);

    /**
     * @param pageNum 举报页数
     * @param pageSize 每页条数
     * @return
     */
    PageInfo<Report> getReports(Integer pageNum, Integer pageSize);

}
