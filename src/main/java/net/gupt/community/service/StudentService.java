package net.gupt.community.service;

import net.gupt.community.entity.Student;

/**
 * 学生服务接口
 *
 * @author Cui
 */
public interface StudentService {

    Student loginByUid(Integer uid);

    Student loginByOpenId(String openId);

    boolean userBinding(Student student);
}
