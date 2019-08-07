package net.gupt.community.controller;

import com.github.pagehelper.PageInfo;
import net.gupt.community.annotation.AuthToken;
import net.gupt.community.entity.CodeMsg;
import net.gupt.community.entity.PageInfoBean;
import net.gupt.community.entity.Report;
import net.gupt.community.entity.Result;
import net.gupt.community.service.ReportService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * <h3>gupt-community</h3>
 * <p>举报控制层</p>
 *
 * @author : Cui
 * @date : 2019-07-30 02:36
 **/
@AuthToken
@Controller
@RequestMapping(value = "/report", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @ResponseBody
    @RequestMapping(value = "/postReport", method = RequestMethod.POST)
    public Result postReport(@RequestBody Report report) {
        int sqlResult = reportService.postReport(report);
        if (sqlResult == 0) {
            return Result.error(CodeMsg.FAILED);
        }
        return Result.success(CodeMsg.SUCCESS);
    }

    @ResponseBody
    @RequestMapping(value = "/getReports", method = RequestMethod.GET)
    public Result getReports(@RequestParam(value = "pageNum") Integer pageNum,
                                @RequestParam(value = "pageSize") Integer pageSize) {
        PageInfo<Report> reportPage = reportService.getReports(pageNum, pageSize);
        if (reportPage == null) {
            return Result.error(CodeMsg.FAILED);
        }
        PageInfoBean<Report> pageInfoPageInfoBean = new PageInfoBean<>(reportPage);
        return Result.success(CodeMsg.SUCCESS, pageInfoPageInfoBean);
    }

}
