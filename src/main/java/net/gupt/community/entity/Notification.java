package net.gupt.community.entity;

import lombok.Data;

import java.util.Date;

/**
 * ClassName  Notification <br/>
 * Description  通知实体类<br/>
 *
 * @author YG
 * @version 1.0
 * @date 2019/10/20 10:01<br/>
 * @since JDK 1.8
 */

@Data
public class Notification {
    private Integer id;
    private String title;
    private String topic;
    private Byte type;
    private Date createTime = new Date();
    private Date lastModifyTime = new Date();
    private Integer lastVersion;
}
