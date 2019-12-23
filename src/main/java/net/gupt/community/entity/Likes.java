package net.gupt.community.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Description 点赞实体类<br/>
 *
 * @author YG <br/>
 * @date 2019/10/8 17:07<br/>
 */
@Data
public class Likes implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer uid;
    private Integer articleId;
    private Byte articleType;
    private Integer loveNum = 0;
    private Integer viewNum = 0;
}