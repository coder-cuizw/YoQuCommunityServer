package net.gupt.community.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import net.gupt.community.entity.Common;
import net.gupt.community.entity.Img;
import net.gupt.community.entity.Likes;
import net.gupt.community.entity.Student;

import java.io.Serializable;
import java.util.List;

/**
 * ClassName  CommonVo <br/>
 * Description Common View Object  <br/>
 *
 * @author Administrator
 * @version 1.0
 * @date 2019/10/1010:09<br/>
 * @since JDK 1.8
 */
@Getter
@Setter
public class CommonVo extends Common implements Serializable {

    @JsonIgnoreProperties("registerTime")
    private Student student;

    private List<Img> img;

    private Likes likes;

}
