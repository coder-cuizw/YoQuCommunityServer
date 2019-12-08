package net.gupt.community.mapper;

import net.gupt.community.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface StudentMapper {
    /**
     * 查询学生信息
     *
     * @param record 学生信息记录
     * @return int
     */
    int insert(Student record);


    /**
     * Description 通过学号查询学生信息<br/>
     *
     * @param uid <br/>
     * @return Student
     * @date 2019/10/8 16:42<br/>
     */
    Student findStudentByUid(Integer uid);


    /**
     * Description 通过学号获取信息列表 <br/>
     *
     * @param uid <br/>
     * @return student
     * @author YG <br/>
     * @date 2019/12/6 15:55<br/>
     */
    Student findUserInfo(Integer uid);

    /**
     * Description 通过openId查询学生信息<br/>
     *
     * @param openId <br/>
     * @return Student
     * @date 2019/10/8 16:42<br/>
     */
    Student findStudentByOpenId(String openId);

    /**
     * 更新小程序用户信息
     *
     * @param openId    微信的用户唯一标识
     * @param nickName  微信用户的名称
     * @param sex       性别
     * @param avatarUrl 微信用户的头像
     * @return int
     */
    int updateStudentWxInfo(String openId, String nickName, String avatarUrl, Byte sex);
}