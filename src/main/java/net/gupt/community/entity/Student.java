package net.gupt.community.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * Description 学生实体类 <br/>
 *
 * @author YG <br/>
 * @date 2019/10/8 17:09<br/>
 */
@Data
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer uid;
    private String realName;
    private Byte sex;
    private Boolean state;
    private Date registerTime = new Date();
    private Date forbidTime;
    private Boolean permission;
    private String unionId;
    private String openId;
    private String nickName;
    private String avatarUrl;
    private String className;
}