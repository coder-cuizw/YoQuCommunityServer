package net.gupt.community.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.gupt.community.entity.CodeMsg;
import net.gupt.community.entity.Notification;
import net.gupt.community.entity.Result;
import net.gupt.community.mapper.NotificationMapper;
import net.gupt.community.service.NotificationService;
import org.springframework.stereotype.Service;

/**
 * ClassName  NotificationServiceImpl <br/>
 * Description  <br/>
 *
 * @author Administrator
 * @version 1.0
 * @date 2019/10/20 13:38<br/>
 * @since JDK 1.8
 */
@Service
public class NotificationServiceImpl implements NotificationService {
    private final NotificationMapper notificationMapper;

    public NotificationServiceImpl(NotificationMapper notificationMapper) {
        this.notificationMapper = notificationMapper;
    }

    @Override
    public PageInfo<Notification> getNotifications(Integer pageNum, Integer pageSize, Byte type) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(notificationMapper.findNotification(type));
    }

    /**
     * 发送通知
     *
     * @param notification 通知实体类
     * @return result
     */
    @Override
    public Result postNotification(Notification notification, boolean permission) {
        if (permission) {
            int rows = notificationMapper.insertNotification(notification);
            if (rows > 0) {
                return Result.success(CodeMsg.SUCCESS, notification.getId());
            }
        }
        return Result.error(CodeMsg.POST_FAILED);
    }

    /**
     * 更新通知
     *
     * @param notification 通知对象
     * @return Result
     */
    @Override
    public Result updateNotification(Notification notification, boolean permission) {
        if (permission) {
            int rows = notificationMapper.updateNotification(notification);
            if (rows > 0) {
                return Result.success(CodeMsg.SUCCESS);
            }
        }
        return Result.error(CodeMsg.UPDATE_FAILED);
    }

    /**
     * 删除通知
     *
     * @param id 主键ID
     * @return Result
     */
    @Override
    public Result deleterNotification(Integer id, boolean permission) {
        if (permission) {
            int result = notificationMapper.deleterNotification(id);
            if (result > 0) {
                return Result.success(CodeMsg.SUCCESS);
            }
        }
        return Result.error(CodeMsg.DELETE_FAILED);
    }
}
