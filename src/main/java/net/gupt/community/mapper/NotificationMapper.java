package net.gupt.community.mapper;

import net.gupt.community.entity.Notification;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ClassName  NotificationMapper <br/>
 * Description 通知Mapper <br/>
 *
 * @author Administrator
 * @version 1.0
 * @date 2019/10/20 10:10<br/>
 * @since JDK 1.8
 */
@Mapper
@Component
public interface NotificationMapper {
    /**
     * 查询通知
     *
     * @param type    通知类型
     * @return Notification
     */
    List<Notification> findNotification(Byte type);

    /**
     * 发送通知
     *
     * @param notification 通知实体类
     * @return int
     */
    int insertNotification(Notification notification);

    /**
     * 更新通知
     *
     * @param notification 通知对象
     * @return int
     */
    int updateNotification(Notification notification);

    /**
     * 删除通知
     *
     * @param id 主键ID
     * @return int
     */
    int deleterNotification(Integer id);
}
