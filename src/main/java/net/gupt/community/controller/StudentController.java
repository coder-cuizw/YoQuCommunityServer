package net.gupt.community.controller;

import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import net.gupt.community.annotation.AuthToken;
import net.gupt.community.annotation.LimitFrequency;
import net.gupt.community.entity.*;
import net.gupt.community.service.CommonService;
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
@Slf4j
@AuthToken
@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class StudentController {

    private final StudentService studentService;
    private final CommonService commonService;
    private final FoundService foundService;
    private final Found found;
    private Student student;

    private final String open_id = "OPEN_ID";

    public StudentController(StudentService studentService, CommonService commonService, FoundService foundService, Found found, Student student) {
        this.studentService = studentService;
        this.commonService = commonService;
        this.foundService = foundService;
        this.found = found;
        this.student = student;
    }

    /**
     * 用户登陆
     *
     * @param request request
     * @return 结果集输出信息
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Result userLogin(HttpServletRequest request) {
        String openId = request.getAttribute(open_id).toString();
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
    @LimitFrequency(count = 3)
    @RequestMapping(value = "/binding", method = RequestMethod.POST)
    public Result userBinding(@RequestBody Student student) {
        boolean success = studentService.userBinding(student);
        if (!success) {
            return Result.error(CodeMsg.FAILED);
        }
        return Result.success(CodeMsg.SUCCESS);
    }

    /**
     * 获取个人帖子数据
     *
     * @param postType 帖子类型
     * @param pageNum  页数
     * @param pageSize 每页条数
     * @return 结果集输出信息
     */
    @RequestMapping(value = "/getMyArticles", method = RequestMethod.GET)
    public Result getMyArticles(@RequestParam(value = "postType") Byte postType,
                                @RequestParam(value = "pageNum") Integer pageNum,
                                @RequestParam(value = "pageSize") Integer pageSize,
                                @RequestParam(value = "id", required = false) Integer id,
                                HttpServletRequest request) {
        student = (Student) request.getAttribute("Student");
        //获取学号作为查询条件
        Integer uid = student.getUid();
        PageInfo<Common> articles = commonService.getArticles(postType, pageNum, pageSize, uid, id, null);
        if (articles == null) {
            return Result.error(CodeMsg.FAILED);
        }
        return Result.success(CodeMsg.SUCCESS, new PageInfoBean<>(articles));
    }

    /**
     * Description 获取个人失物帖子<br/>
     *
     * @param pageNum      <br/>
     * @param pageSize     <br/>
     * @param articleState <br/>
     * @param request      <br/>
     * @return Result
     * @author YG<br />
     * @date 2019/9/4 21:58<br/>
     */
    @GetMapping(value = "/getMyFounds")
    public Result getFoundsByUser(@RequestParam(value = "articleState", required = false) Boolean articleState,
                                  @RequestParam(value = "id", required = false) Integer id,
                                  @RequestParam(value = "pageNum") Integer pageNum,
                                  @RequestParam(value = "pageSize") Integer pageSize,
                                  HttpServletRequest request) {
        final String studentObject = "Student";
        found.setArticleState(articleState);
        found.setId(id);
        student = (Student) request.getAttribute(studentObject);
        FoundQueryVo query = new FoundQueryVo(found, student);
        PageInfo<Found> foundPageInfo = foundService.getFounds(pageNum, pageSize, query);
        if (foundPageInfo == null) {
            return Result.error(CodeMsg.FAILED);
        }
        return Result.success(CodeMsg.SUCCESS, new PageInfoBean<>(foundPageInfo));

    }

    /**
     * 更新微信用户头像和昵称
     *
     * @param student 学生
     * @param request 请求信息
     * @return 结果
     */
    @RequestMapping(value = "/updateWxInfo", method = RequestMethod.POST)
    @LimitFrequency(count = 3)
    public Result updateWxInfo(@RequestBody Student student,
                               HttpServletRequest request) {
        String openId = request.getAttribute(open_id).toString();
        String nickName = student.getNickName();
        System.out.println(nickName);
        String avatarUrl = student.getAvatarUrl();
        boolean isSuccess = studentService.updateWxInfo(openId, nickName, avatarUrl);
        if (!isSuccess) {
            return Result.error(CodeMsg.FAILED);
        }
        return Result.success(CodeMsg.SUCCESS);
    }

}
