package net.gupt.community.vo;

import lombok.Data;
import net.gupt.community.entity.Img;
import net.gupt.community.entity.Report;

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
@Data
public class ReportVo extends Report implements Serializable {
    private List<Img> img;
}
