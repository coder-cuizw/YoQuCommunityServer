package net.gupt.community.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 举报实体类
 *
 * @author Cui
 */
@Data
public class Report implements Serializable {
    private Integer id;

    private Integer uid;

    private String reportType;

    private Date reportTime = new Date();

    private Boolean state;

    private Byte articleType;

    private Integer articleId;

    private String content;

    private static final long serialVersionUID = 1L;

}