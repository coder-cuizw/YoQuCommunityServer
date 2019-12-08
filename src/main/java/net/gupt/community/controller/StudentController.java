package net.gupt.community.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import net.gupt.community.annotation.AuthToken;
import net.gupt.community.annotation.LimitFrequency;
import net.gupt.community.annotation.VisitorLimit;
import net.gupt.community.entity.*;
import net.gupt.community.service.CommonService;
import net.gupt.community.service.FoundService;
import net.gupt.community.service.StudentService;
import net.gupt.community.vo.CommonVo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <h3>gupt-community</h3>
 * <p>用户控制层</p>
 *
 * @author : Cui
 * @date : 2019-07-29 05:21
 **/
@Slf4j
@AuthToken
@Api(value = "用户信息", protocols = "http", tags = "用户信息接口")
@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class StudentController {

    private final StudentService studentService;
    private final CommonService commonService;
    private final FoundService foundService;
    private final HttpServletRequest request;
    private final String stu = "Student";

    public StudentController(StudentService studentService, CommonService commonService, FoundService foundService, HttpServletRequest request) {
        this.studentService = studentService;
        this.commonService = commonService;
        this.foundService = foundService;
        this.request = request;
    }

    /**
     * 用户登陆
     *
     * @return 结果集输出信息
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Result userLogin() {
        Student student = (Student) request.getAttribute(stu);
        return student == null ?
                Result.error(CodeMsg.LOGIN_FAILED) : Result.success(CodeMsg.SUCCESS, student);
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
        return !success ? Result.error(CodeMsg.BINDING_FAILED) : Result.success(CodeMsg.SUCCESS);
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
                                @RequestParam(value = "id", required = false) Integer id) {
        Student student = (Student) request.getAttribute(stu);
        //获取学号作为查询条件
        Integer uid = student.getUid();
        PageInfo<CommonVo> articles = commonService.getArticles(postType, pageNum, pageSize, uid, id, null, null, null);
        return articles == null ?
                Result.error(CodeMsg.FAILED) : Result.success(CodeMsg.SUCCESS, new PageInfoBean<>(articles));
    }

    /**
     * Description 获取个人失物帖子<br/>
     *
     * @param pageNum      <br/>
     * @param pageSize     <br/>
     * @param articleState <br/>
     * @return Result
     * @author YG<br />
     * @date 2019/9/4 21:58<br/>
     */
    @GetMapping(value = "/getMyFounds")
    public Result getFoundsByUser(@RequestParam(value = "articleState", required = false) Boolean articleState,
                                  @RequestParam(value = "id", required = false) Integer id,
                                  @RequestParam(value = "pageNum") Integer pageNum,
                                  @RequestParam(value = "pageSize") Integer pageSize) {
        Student student;
        student = (Student) request.getAttribute(stu);
        Integer uid = student.getUid();
        PageInfo<Found> foundPageInfo = foundService.getFounds(pageNum, pageSize, id, articleState, null, uid, null, null);
        return foundPageInfo == null ?
                Result.error(CodeMsg.FAILED) : Result.success(CodeMsg.SUCCESS, new PageInfoBean<>(foundPageInfo));
    }

    /**
     * 更新微信用户头像和昵称
     *
     * @param student 学生
     * @return 结果
     */
    @VisitorLimit
    @RequestMapping(value = "/updateWxInfo", method = RequestMethod.POST)
    @LimitFrequency(count = 3)
    public Result updateWxInfo(@RequestBody Student student) {
        String openId = ((Student) request.getAttribute(stu)).getOpenId();
        String nickName = student.getNickName();
        Byte sex = student.getSex();
        String avatarUrl = student.getAvatarUrl();
        boolean isSuccess = studentService.updateWxInfo(openId, nickName, avatarUrl, sex);
        return !isSuccess ? Result.error(CodeMsg.UPDATE_FAILED) : Result.success(CodeMsg.SUCCESS);
    }

    @GetMapping("/getUserInfo")
    public Result getUserInfo(@RequestParam(value = "pageNum") Integer pageNum,
                              @RequestParam(value = "pageSize") Integer pageSize,
                              @RequestParam List<Integer> uid) {
        log.info(uid.toString());
        PageInfo<List<Student>> userInfo = studentService.getUserInfo(uid, pageNum, pageSize);
        return userInfo == null ?
                Result.error(CodeMsg.FAILED) : Result.success(CodeMsg.SUCCESS, new PageInfoBean<>(userInfo));
    }
}
