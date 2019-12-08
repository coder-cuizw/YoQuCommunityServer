package net.gupt.community.service;

import com.github.pagehelper.PageInfo;
import net.gupt.community.entity.Student;

import java.util.List;

/**
 * 学生服务接口
 *
 * @author Cui
 */
public interface StudentService {

    /**
     * 通过UID登陆
     *
     * @param uid 学号
     * @return Student
     */
    Student loginByUid(Integer uid);

    /**
     * 通过openid登陆
     *
     * @param openId 微信唯一标识
     * @return student
     */
    Student loginByOpenId(String openId);

    /**
     * 绑定用户
     *
     * @param student 学生
     * @return boolean
     */
    boolean userBinding(Student student);

    /**
     * 更新微信用户信息
     *
     * @param openId    微信用户唯一标识
     * @param nickName  微信名称
     * @param avatarUrl 微信头像连接
     * @param sex       性别
     * @return boolean
     */
    boolean updateWxInfo(String openId, String nickName, String avatarUrl, Byte sex);


    /**
     * Description 通过学号获取信息 <br/>
     *
     * @param uid      <br/>
     * @param pageNum  <br/>
     * @param pageSize <br/>
     * @return list
     * @author YG <br/>
     * @date 2019/12/6 16:04<br/>
     */
    PageInfo<List<Student>> getUserInfo(List<Integer> uid, Integer pageNum, Integer pageSize);
}
