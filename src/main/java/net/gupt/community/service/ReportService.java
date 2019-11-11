package net.gupt.community.service;

import com.github.pagehelper.PageInfo;
import net.gupt.community.entity.Report;
import net.gupt.community.entity.Result;
import net.gupt.community.entity.Student;

/**
 * 举报服务接口
 *
 * @author Cui
 */
public interface ReportService {

    /**
     * 获取举报列表
     *
     * @param pageNum  举报页数
     * @param pageSize 每页条数
     * @return PageInfo<Report>
     */
    PageInfo<Report> getReports(Integer pageNum, Integer pageSize);

    /**
     * 删除举报
     *
     * @param id 主键id
     * @return int
     */
    Result deleteReport(Integer id, Student student);


    /**
     * 发送举报
     *
     * @param report  举报信息
     * @param student 学生对象
     * @return 返回执行状态码
     */
    Result postReport(Report report, Student student);
}
