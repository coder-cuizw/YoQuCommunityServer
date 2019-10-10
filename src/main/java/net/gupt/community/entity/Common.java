package net.gupt.community.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * Description 通用帖子实体类 <br/>
 *
 * @author YG <br/>
 * @date 2019/10/8 17:06<br/>
 */
@Data
public class Common implements Serializable {
    private Integer id;

    private Integer uid;

    private String title;

    private Byte postType;

    private Date postTime = new Date();

    private String postContent;

    private Boolean isAnonymous;

    private Boolean top;

    private static final long serialVersionUID = 1L;

}