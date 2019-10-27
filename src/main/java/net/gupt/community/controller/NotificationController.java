package net.gupt.community.controller;

import com.github.pagehelper.PageInfo;
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
        if (permission) {
            int result = notificationService.postNotification(notification);
            if (result > 0) {
                return Result.success(CodeMsg.POST_SUCCESS, notification.getId());
            }
        }
        return Result.error(CodeMsg.POST_FAILED);
    }

    @PostMapping("/updateNotification")
    public Result updateNotification(@RequestBody Notification notification) {
        Student student = (Student) request.getAttribute(stu);
        boolean permission = student.getPermission();
        if (permission) {
            int result = notificationService.updateNotification(notification);
            if (result > 0) {
                return Result.success(CodeMsg.UPDATE_SUCCESS);
            }
        }
        return Result.error(CodeMsg.UPDATE_FAILED);
    }

    @GetMapping("/getNotification")
    public Result getNotification(@RequestParam Integer pageNum,
                                  @RequestParam Integer pageSize,
                                  @RequestParam Byte type) {
        PageInfo<Notification> notificationPageInfo = notificationService.getNotifications(pageNum, pageSize, type);
        return notificationPageInfo != null ?
                Result.success(CodeMsg.SUCCESS, new PageInfoBean<>(notificationPageInfo)) : Result.error(CodeMsg.FAILED);
    }

    @GetMapping("/deleteNotification")
    public Result deleterNotification(@RequestParam Integer id) {
        Student student = (Student) request.getAttribute(stu);
        boolean permission = student.getPermission();
        if (permission) {
            int result = notificationService.deleterNotification(id);
            if (result > 0) {
                return Result.success(CodeMsg.DELETE_SUCCESS);
            }
        }
        return Result.error(CodeMsg.DELETE_FAILED);
    }
}
