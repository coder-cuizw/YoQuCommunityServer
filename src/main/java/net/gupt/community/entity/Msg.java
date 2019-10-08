package net.gupt.community.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * Description 私信实体类  <br/>
 * @author  YG <br/>
 * @date   2019/10/8 17:08<br/>
 */
@Data
public class Msg implements Serializable {
    private Integer id;

    private Integer posterId;

    private Integer receiverId;

    private Date time;

    private String content;

    private static final long serialVersionUID = 1L;


}