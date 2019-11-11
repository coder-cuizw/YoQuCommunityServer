package net.gupt.community.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * Description 失物实体类 <br/>
 *
 * @author YG <br/>
 * @date 2019/10/8 17:07<br/>
 */
@Data
public class Found implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer uid;
    private String title;
    private Date postTime = new Date();
    private String contactNumber;
    private String lostTime;
    private String lostName;
    private String lostClass;
    private String lostDescribe;
    private String address;
    private Boolean articleState;
    private Boolean top;
}