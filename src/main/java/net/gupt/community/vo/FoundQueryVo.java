package net.gupt.community.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import net.gupt.community.entity.Found;
import net.gupt.community.entity.Student;

import java.io.Serializable;

/**
 * ClassName  FoundQueryVo <br/>
 * Description 失物找回查询条件对象包装类 <br/>
 *
 * @author YG
 * @version 1.0
 * @date 2019/8/617:36<br/>
 * @since JDK 1.8
 */

@Data
public class FoundQueryVo  implements Serializable {
    private Found found;
    @JsonIgnoreProperties("registerTime")
    private Student student;
    public FoundQueryVo() {
    }

    public FoundQueryVo(Found found, Student student) {
        this.found = found;
        this.student = student;
    }
}
