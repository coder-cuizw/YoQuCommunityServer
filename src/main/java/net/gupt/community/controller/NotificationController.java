package net.gupt.community.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import net.gupt.community.annotation.AuthToken;
import net.gupt.community.entity.*;
import net.gupt.community.service.NotificationService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * ClassName  NotificationController <br/>
 * Description 通知控制成层 <br/>
 *
 * @author YG
 * @version 1.0
 * @date 2019/10/20 13:48<br/>
 * @since JDK 1.8
 */
@Slf4j
@AuthToken
@Api(value = "通知", protocols = "http", tags = "通知接口")
@RestController
@RequestMapping(value = "notification", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class NotificationController {
    private final NotificationService notificationService;
    private final HttpServletRequest request;
    private final String stu = "Student";

    public NotificationController(NotificationService notificationService, HttpServletRequest request) {
        this.notificationService = notificationService;
        this.request = request;
    }

    @PostMapping("/postNotification")
    public Result postNotification(@RequestBody Notification notification) {
        Student student = (Student) request.getAttribute(stu);
        boolean permission = student.getPermission();
        return notificationService.postNotification(notification, permission);
    }

    @PostMapping("/updateNotification")
    public Result updateNotification(@RequestBody Notification notification) {
        Student student = (Student) request.getAttribute(stu);
        boolean permission = student.getPermission();
        return notificationService.updateNotification(notification, permission);

    }

    @GetMapping("/getNotification")
    public Result getNotification(@RequestParam Integer pageNum,
                                  @RequestParam Integer pageSize,
                                  @RequestParam Byte type) {
        PageInfo<Notification> notificationPageInfo = notificationService.getNotifications(pageNum, pageSize, type);
        return notificationPageInfo != null ?
                Result.success(CodeMsg.SUCCESS, new PageInfoBean<>(notificationPageInfo)) : Result.error(CodeMsg.FAILED);
    }

    @DeleteMapping("/deleteNotification")
    public Result deleterNotification(@RequestParam Integer id) {
        Student student = (Student) request.getAttribute(stu);
        boolean permission = student.getPermission();
        return notificationService.deleterNotification(id, permission);
    }
}
