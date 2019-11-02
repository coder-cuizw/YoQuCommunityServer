package net.gupt.community.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.gupt.community.entity.Found;
import net.gupt.community.entity.Img;
import net.gupt.community.entity.Likes;
import net.gupt.community.entity.Student;

import java.io.Serializable;
import java.util.List;

/**
 * ClassName  FoundVo <br/>
 * Description 失物找回查询条件对象包装类 <br/>
 *
 * @author YG
 * @version 1.0
 * @date 2019/8/617:36<br/>
 * @since JDK 1.8
 */
@Getter
@Setter
@NoArgsConstructor
public class FoundVo extends Found implements Serializable {

    @JsonIgnoreProperties("registerTime")
    private Student student;

    private List<Img> img;

    private Likes likes;
}
