package net.gupt.community.controller;

import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import net.gupt.community.annotation.AuthToken;
import net.gupt.community.annotation.LimitFrequency;
import net.gupt.community.entity.*;
import net.gupt.community.service.FoundService;
import net.gupt.community.vo.FoundVo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <h3>gupt-community</h3>
 * <p>失物找回web控制层</p>
 *
 * @author : Cui
 * @date : 2019-07-30 21:01
 **/
@Slf4j
@AuthToken
@RestController
@RequestMapping(value = "/found", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class FoundController {

    private final FoundService foundService;
    private final HttpServletRequest request;
    private final String stu = "Student";
    private Student student;


    public FoundController(FoundService foundService, HttpServletRequest request) {
        this.foundService = foundService;
        this.request = request;
    }

    /**
     * Description 查询所有失物信息<br/>
     *
     * @param pageNum      查询的页数
     * @param pageSize     页面数据多少
     * @param articleState 失物状态
     * @return Result
     * @author YG<br />
     * @date 2019/8/8 9:58<br/>
     */
    @GetMapping(value = "/getArticles")
    public Result getFounds(@RequestParam(value = "pageNum") Integer pageNum,
                            @RequestParam(value = "pageSize") Integer pageSize,
                            @RequestParam(value = "articleState", required = false) Boolean articleState,
                            @RequestParam(value = "isTop", required = false) Boolean isTop,
                            @RequestParam(value = "id", required = false) Integer id,
                            @RequestParam(value = "isSearch", required = false) Boolean isSearch,
                            @RequestParam(value = "searchContent", required = false) String searchContent) {

        PageInfo<Found> foundPageInfo = foundService.getFounds(pageNum, pageSize, id, articleState, isTop, null, isSearch, searchContent);
        return foundPageInfo == null ?
                Result.error(CodeMsg.FAILED) : Result.success(CodeMsg.SUCCESS, new PageInfoBean<>(foundPageInfo));
    }

    /**
     * Description 发送失物信息 <br/>
     *
     * @return Result
     * @author YG <br/>
     * @date 2019/8/8 10:00<br/>
     */
    @LimitFrequency(count = 3)
    @PostMapping(value = "/postArticle")
    public Result postFound(@RequestBody FoundVo foundVo) {
        student = (Student) request.getAttribute(stu);
        foundVo.setUid(student.getUid());
        return foundService.postFound(foundVo);
    }

    /**
     * Description 更新失物信息<br/>
     *
     * @param found <br/>
     * @return Result
     * @author YG <br/>
     * @date 2019/8/8 10:00<br/>
     */
    @LimitFrequency(count = 5)
    @PostMapping(value = "/updateFoundStatus")
    public Result updateFoundStatus(@RequestBody Found found) {
        return foundService.updateFoundStatus(found);
    }

    /**
     * Description 删除失物信息 br/>
     *
     * @param id <br/>
     * @return Result
     * @author YG <br/>
     * @date 2019/8/8 10:01<br/>
     */
    @DeleteMapping("/deleteArticle")
    public Result deleteFoundInfo(@RequestParam(value = "id") Integer id,
                                  @RequestParam(value = "uid") Integer uid) {
        student = (Student) request.getAttribute(stu);
        return foundService.deleteFoundInfo(id, uid, student);
    }
}
