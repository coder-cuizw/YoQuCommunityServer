package net.gupt.community.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.gupt.community.entity.Notification;
import net.gupt.community.mapper.NotificationMapper;
import net.gupt.community.service.NotificationService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
@CacheConfig(cacheNames = {"notification"})
@Service
public class NotificationServiceImpl implements NotificationService {
    private final NotificationMapper notificationMapper;

    public NotificationServiceImpl(NotificationMapper notificationMapper) {
        this.notificationMapper = notificationMapper;
    }

    @Cacheable
    @Override
    public PageInfo<Notification> getNotifications(Integer pageNum, Integer pageSize, Byte type) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(notificationMapper.findNotification(type));
    }

    @CacheEvict(allEntries = true)
    @Override
    public int postNotification(Notification notification) {
        return notificationMapper.insertNotification(notification);
    }

    @CacheEvict(allEntries = true)
    @Override
    public int updateNotification(Notification notification) {
        return notificationMapper.updateNotification(notification);
    }


    @CacheEvict(allEntries = true)
    @Override
    public int deleterNotification(Integer id) {
        return notificationMapper.deleterNotification(id);
    }
}
