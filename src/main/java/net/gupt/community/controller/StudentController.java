package net.gupt.community.controller;

import net.gupt.community.annotation.AuthToken;
import net.gupt.community.entity.CodeMsg;
import net.gupt.community.entity.Result;
import net.gupt.community.entity.Student;
import net.gupt.community.service.StudentService;
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

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
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

}
