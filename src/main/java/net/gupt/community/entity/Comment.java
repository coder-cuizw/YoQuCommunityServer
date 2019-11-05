package net.gupt.community.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * Description 评论实体类  <br/>
 *
 * @author YG <br/>
 * @date 2019/10/8 17:05<br/>
 */
@Data
public class Comment implements Serializable {
    private Integer id;

    private Integer uid;

    private Byte type;

    private Integer articleId;

    private Integer replyUid;

    private String replyNickname;

    private Date createTime = new Date();

    private String content;

    private static final long serialVersionUID = 1L;

}