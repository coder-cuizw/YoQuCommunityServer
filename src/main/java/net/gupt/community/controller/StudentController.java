package net.gupt.community.controller;

import com.github.pagehelper.PageInfo;
import net.gupt.community.annotation.AuthToken;
import net.gupt.community.entity.*;
import net.gupt.community.service.FoundService;
import net.gupt.community.service.StudentService;
import net.gupt.community.vo.FoundQueryVo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <h3>gupt-community</h3>
 * <p>用户控制层</p>
 *
 * @author : Cui
 * @date : 2019-07-29 05:21
 **/
@AuthToken
@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class StudentController {

    private final StudentService studentService;
    private final FoundService foundService;
    private static Found found;
    private static Student student;

    public StudentController(StudentService studentService, FoundService foundService) {
        this.studentService = studentService;
        this.foundService = foundService;
        found = new Found();
        student = new Student();
    }

    /**
     * 用户登陆
     *
     * @param request request
     * @return 结果集输出信息
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Result userLogin(HttpServletRequest request) {
        String openId = request.getAttribute("OPEN_ID").toString();
        Student student = studentService.loginByOpenId(openId);
        if (student == null) {
            return Result.error(CodeMsg.FAILED);
        }
        return Result.success(CodeMsg.SUCCESS, student);
    }

    /**
     * 用户绑定
     *
     * @param student 学生信息
     * @return 结果集输出信息
     */
    @RequestMapping(value = "/binding", method = RequestMethod.POST)
    public Result userBinding(@RequestBody Student student) {
        boolean success = studentService.userBinding(student);
        if (!success) {
            return Result.error(CodeMsg.FAILED);
        }
        return Result.success(CodeMsg.SUCCESS);
    }

    /**
     * Description 查询个人失物帖子<br/>
     * @author  YG<br/>
     * @date   2019/9/4 21:58<br/>
     * @param  pageNum <br/>
     * @param pageSize <br/>
     * @param articleState <br/>
     * @param query <br/>
     * @param request <br/>
     * @return
     */
    @RequestMapping(value = "/getMyFounds", method = RequestMethod.GET)
    public Result getFoundsByUser(@RequestParam(value = "articleState", required = false) Boolean articleState,
                                  @RequestParam(value = "pageNum") Integer pageNum,
                                  @RequestParam(value = "pageSize") Integer pageSize,
                                  FoundQueryVo query,
                                  HttpServletRequest request) {
        found.setArticleState(articleState);
        student.setOpenId(request.getAttribute("OPEN_ID").toString());
        query.setFound(found);
        query.setStudent(student);
        PageInfo<Found> foundPageInfo = foundService.getFounds(pageNum, pageSize, query);
        if (foundPageInfo == null) {
            return Result.error(CodeMsg.FAILED);
        }
        return Result.success(CodeMsg.SUCCESS, new PageInfoBean<>(foundPageInfo));


    }

}
