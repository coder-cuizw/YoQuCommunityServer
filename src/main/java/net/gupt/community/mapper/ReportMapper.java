package net.gupt.community.mapper;

import net.gupt.community.entity.Report;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ReportMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Report record);

    int insertSelective(Report record);

    Report selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Report record);

    int updateByPrimaryKeyWithBLOBs(Report record);

    int updateByPrimaryKey(Report record);

    List<Report> findAllReport();
}