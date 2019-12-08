package net.gupt.community.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.websocket.Session;
import java.io.Serializable;
import java.util.Date;


/**
 * Description 私信实体类  <br/>
 *
 * @author YG <br/>
 * @date 2019/10/8 17:08<br/>
 */
@Data
@JsonIgnoreProperties("session")
public class Msg implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer posterUid;
    private Integer receiverUid;
    private String content;
    private Date postTime = new Date();
    private Boolean isObtain;
    private Session session;
}