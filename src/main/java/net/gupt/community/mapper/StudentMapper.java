package net.gupt.community.mapper;

import net.gupt.community.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface StudentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer id);

    Student findStudentByUid(Integer uid);

    Student findStudentByOpenId(String openId);

    int updateStudentWxInfo(String openId, String nickName, String avatarUrl);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
}