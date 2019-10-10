package net.gupt.community.vo;

import lombok.Getter;
import lombok.Setter;
import net.gupt.community.entity.Comment;
import net.gupt.community.entity.Student;

import java.io.Serializable;

/**
 * ClassName  CommentVo <br/>
 * Description 评论视图对象 <br/>
 *
 * @author Administrator
 * @version 1.0
 * @date 2019/10/1019:23<br/>
 * @since JDK 1.8
 */
@Getter
@Setter
public class CommentVo extends Comment implements Serializable {
    private Student student;
}
