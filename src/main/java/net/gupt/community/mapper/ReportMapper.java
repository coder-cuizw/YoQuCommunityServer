package net.gupt.community.mapper;

import net.gupt.community.entity.Report;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ReportMapper {


    /**
     * Description 添加举报信息 <br/>
     *
     * @param record 举报记录 <br/>
     * @return int
     * @author YG <br/>
     * @date 2019/10/8 16:38<br/>
     */
    int insert(Report record);

    /**
     * Description 查询所有举报信息 <br/>
     *
     * @return List<Report>
     * @author YG <br/>
     * @date 2019/10/8 16:38<br/>
     */
    List<Report> findAllReport();

    /**
     * 删除举报接口
     *
     * @param id 举报表主键I
     * @return int
     */
    int deleteReport(Integer id);
}