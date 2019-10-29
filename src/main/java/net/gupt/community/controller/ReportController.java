package net.gupt.community.controller;

import com.github.pagehelper.PageInfo;
import net.gupt.community.annotation.AuthToken;
import net.gupt.community.annotation.LimitFrequency;
import net.gupt.community.entity.*;
import net.gupt.community.service.ReportService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <h3>gupt-community</h3>
 * <p>举报控制层</p>
 *
 * @author : Cui
 * @date : 2019-07-30 02:36
 **/
@AuthToken
@RestController
@RequestMapping(value = "/report", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ReportController {

    private final ReportService reportService;
    private final HttpServletRequest request;
    private final String stu = "Student";

    public ReportController(ReportService reportService, HttpServletRequest request) {
        this.reportService = reportService;
        this.request = request;
    }

    /**
     * 发布举报
     *
     * @param report 举报信息
     * @return 结果集输出信息
     */
    @LimitFrequency(count = 3)
    @RequestMapping(value = "/postReport", method = RequestMethod.POST)
    public Result postReport(@RequestBody Report report) {
        Student student = (Student) request.getAttribute(stu);
        report.setUid(student.getUid());
        int sqlResult = reportService.postReport(report);
        return sqlResult == 0 ? Result.error(CodeMsg.REPORT_FAILED) : Result.success(CodeMsg.REPORT_SUCCESS);
    }

    /**
     * 获取举报
     *
     * @param pageNum  页数
     * @param pageSize 每页条数
     * @return 结果集输出信息
     */
    @RequestMapping(value = "/getReports", method = RequestMethod.GET)
    public Result getReports(@RequestParam(value = "pageNum") Integer pageNum,
                             @RequestParam(value = "pageSize") Integer pageSize) {
        PageInfo<Report> reportPage = reportService.getReports(pageNum, pageSize);
        return reportPage == null ? Result.error(CodeMsg.FAILED) : Result.success(CodeMsg.SUCCESS, new PageInfoBean<>(reportPage));
    }

    @GetMapping("/deleteReport")
    public Result deleteReport(@RequestParam(value = "id") Integer id) {
        Student student = (Student) request.getAttribute(stu);
        boolean permission = student.getPermission();
        if (permission) {
            int result = reportService.deleteReport(id);
            if (result > 0) {
                return Result.success(CodeMsg.DELETE_SUCCESS);
            }
        }
        return Result.error(CodeMsg.DELETE_FAILED);
    }
}
