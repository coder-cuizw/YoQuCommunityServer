package net.gupt.community.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * Description 失物实体类 <br/>
 *
 * @author YG <br/>
 * @date 2019/10/8 17:07<br/>
 */
@Component
@Data
public class Found implements Serializable {
    private Integer id;

    private Integer uid;

    private String title;

    private Date postTime = new Date();

    private String contactNumber;

    private String lostTime;

    private String lostName;

    private String lostClass;

    private String address;

    private Boolean articleState;

    private Boolean top;


    private String nickName;

    private String avatarUrl;

    private String lostDescribe;

    private List<Img> img;

    private Likes likes;

    private static final long serialVersionUID = 1L;
}