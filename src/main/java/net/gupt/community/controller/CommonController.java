package net.gupt.community.controller;

import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import net.gupt.community.annotation.AuthToken;
import net.gupt.community.annotation.LimitFrequency;
import net.gupt.community.entity.*;
import net.gupt.community.service.CommonService;
import net.gupt.community.vo.CommonVo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <h3>gupt-community</h3>
 * <p>通用帖子web控制层</p>
 *
 * @author : Cui
 * @date : 2019-07-30 16:53
 **/
@Slf4j
@AuthToken
@RestController
@RequestMapping(value = "/common", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CommonController {

    private final CommonService commonService;
    private final HttpServletRequest request;
    private final String stu = "Student";
    private Student student;


    public CommonController(CommonService commonService, HttpServletRequest request) {
        this.commonService = commonService;
        this.request = request;
    }

    /**
     * 获取帖子数据
     *
     * @param postType      帖子类型
     * @param pageNum       页数
     * @param isSearch      是否搜索
     * @param searchContent 是否搜索
     * @param isTop         是否置顶
     * @param id            文章ID
     * @param pageSize      每页条数
     * @return 结果集输出信息
     */
    @RequestMapping(value = "/getArticles", method = RequestMethod.GET)
    public Result getArticles(@RequestParam(value = "postType") Byte postType,
                              @RequestParam(value = "pageNum") Integer pageNum,
                              @RequestParam(value = "pageSize") Integer pageSize,
                              @RequestParam(value = "isTop", required = false) Boolean isTop,
                              @RequestParam(value = "id", required = false) Integer id,
                              @RequestParam(value = "isSearch", required = false) Boolean isSearch,
                              @RequestParam(value = "searchContent", required = false) String searchContent) {
        PageInfo<CommonVo> articles = commonService.getArticles(postType, pageNum, pageSize, null, id, isTop, isSearch, searchContent);
        return articles == null ? Result.error(CodeMsg.FAILED) : Result.success(CodeMsg.SUCCESS, new PageInfoBean<>(articles));
    }

    /**
     * Description 整合发送图片 <br/>
     *
     * @param commonVo <br/>
     * @return Result
     * @author YG <br/>
     * @date 2019/10/8 12:34<br/>
     */
    @LimitFrequency(count = 3)
    @RequestMapping(value = "/postArticle", method = RequestMethod.POST)
    public Result postArticle(@RequestBody CommonVo commonVo) {
        student = (Student) request.getAttribute(stu);
        commonVo.setUid(student.getUid());
        return commonService.postArticle(commonVo);
    }

    /**
     * 设置置顶
     *
     * @param common <br/>
     * @return Result
     */
    @PostMapping("/setTop")
    public Result setTop(@RequestBody Common common) {
        return commonService.setTop(common);
    }

    /**
     * 删除帖子及相关数据
     *
     * @param id          帖子Id
     * @param commonUid   帖子的uid
     * @param articleType 帖子类型
     * @return 结果集输出信息
     */
    @DeleteMapping("/deleteArticle")
    public Result deleteArticle(@RequestParam("articleType") Byte articleType,
                                @RequestParam("id") Integer id,
                                @RequestParam("commonUid") Integer commonUid) {
        student = (Student) request.getAttribute(stu);
        return commonService.deleteArticle(articleType, id, commonUid, student);
    }

}