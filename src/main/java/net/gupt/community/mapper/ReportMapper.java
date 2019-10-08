package net.gupt.community.mapper;

import net.gupt.community.entity.Report;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ReportMapper {


    /**
     * Description 添加举报信息 <br/>
     * @author  YG <br/>
     * @date   2019/10/8 16:38<br/>
     * @param  record 举报记录 <br/>
     * @return int
     */
    int insert(Report record);

    /**
     * Description 查询所有举报信息 <br/>
     * @author  YG <br/>
     * @date   2019/10/8 16:38<br/>
     * @return  List<Report>
     */
    List<Report> findAllReport();
}