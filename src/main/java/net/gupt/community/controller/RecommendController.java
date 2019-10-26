package net.gupt.community.controller;

import com.github.pagehelper.PageInfo;
import net.gupt.community.annotation.AuthToken;
import net.gupt.community.annotation.LimitFrequency;
import net.gupt.community.entity.*;
import net.gupt.community.service.RecommendService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

/**
 * <h3>gupt-community</h3>
 * <p>反馈建议控制层</p>
 *
 * @author : Cui
 * @date : 2019-07-29 19:47
 **/
@AuthToken
@RestController
@RequestMapping(value = "/recommend", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class RecommendController {

    private final RecommendService recommendService;
    private final HttpServletRequest request;

    public RecommendController(RecommendService recommendService, HttpServletRequest request) {
        this.recommendService = recommendService;
        this.request = request;
    }

    /**
     * 发布反馈
     *
     * @param recommend 反馈信息
     * @return 结果集输出信息
     */
    @LimitFrequency(count = 3)
    @RequestMapping(value = "/postRecommend", method = RequestMethod.POST)
    public Result postRecommend(@RequestBody Recommend recommend) {
        int sqlResult = recommendService.postRecommend(recommend);
        if (sqlResult == 0) {
            return Result.error(CodeMsg.RECOMMEND_FAILED);
        }
        return Result.success(CodeMsg.RECOMMEND_SUCCESS);
    }

    /**
     * 获取反馈接口
     *
     * @param pageNum  页数
     * @param pageSize 每页条数
     * @return 结果集输出信息
     */
    @RequestMapping(value = "/getRecommends", method = RequestMethod.GET)
    public Result getRecommends(@RequestParam(value = "pageNum") Integer pageNum,
                                @RequestParam(value = "pageSize") Integer pageSize) {
        PageInfo<Recommend> recommendPageInfo = recommendService.getRecommends(pageNum, pageSize);
        if (recommendPageInfo == null) {
            return Result.error(CodeMsg.FAILED);
        }
        return Result.success(CodeMsg.SUCCESS, new PageInfoBean<>(recommendPageInfo));
    }
    /**
     * 删除反馈接口
     *
     * @param id id
     * @return result
     */
    @GetMapping("/deleteRecommend")
    public Result deleteReport(@RequestParam(value = "id") Integer id) {
        Student student = Student.student(request);
        boolean permission = student.getPermission();
        if (permission) {
            int result = recommendService.deleteRecommend(id);
            if (result > 0) {
                return Result.success(CodeMsg.DELETE_SUCCESS);
            }
        }
        return Result.error(CodeMsg.DELETE_FAILED);
    }
}
