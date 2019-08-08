package net.gupt.community.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.gupt.community.entity.Found;
import net.gupt.community.entity.Student;

/**
 * ClassName  FoundQueryVo <br/>
 * Description 失物找回查询条件对象包装类 <br/>
 *
 * @author YG
 * @version 1.0
 * @date 2019/8/617:36<br/>
 * @since JDK 1.8
 */
@Getter
@Setter
@ToString
public class FoundQueryVo {
    private Found found;
    private Student student;
}
