package net.gupt.community.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Description 反馈实体类 <br/>
 * @author  YG <br/>
 * @date   2019/10/8 17:08<br/>
 */
@Data
public class Recommend implements Serializable {
    private Integer id;

    private Integer uid;

    private Date time = new Date();

    private String content;

    private static final long serialVersionUID = 1L;
}