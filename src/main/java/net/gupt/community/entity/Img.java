package net.gupt.community.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;


/**
 * Description 图片实体类 <br/>
 *
 * @author YG <br/>
 * @date 2019/10/8 17:07<br/>
 */
@Data
@JsonIgnoreProperties(value = {"id","articleType","articleId"})
public class Img implements Serializable {
    private Integer id;

    private String imgUrl;

    private Byte articleType;

    private Integer articleId;

    private static final long serialVersionUID = 1L;


}