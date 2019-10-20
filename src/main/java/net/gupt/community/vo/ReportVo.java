package net.gupt.community.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import net.gupt.community.entity.Common;
import net.gupt.community.entity.Img;
import net.gupt.community.entity.Report;
import net.gupt.community.entity.Student;

import java.io.Serializable;
import java.util.List;

/**
 * ClassName  ReportVo <br/>
 * Description  <br/>
 *
 * @author Administrator
 * @version 1.0
 * @date 2019/10/1320:58<br/>
 * @since JDK 1.8
 */
@Getter
@Setter
public class ReportVo extends Report implements Serializable {
    private Common common;
    @JsonIgnoreProperties("registerTime")
    private Student student;
    private List<Img> img;
}
