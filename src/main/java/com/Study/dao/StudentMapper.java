package com.Study.dao;

import com.Study.bean.LoginForm;
import com.Study.bean.Student;

import java.util.List;

/**
 * @description:
 * @Author: Mutong
 * @Date: 2019.10.13 13:39
 */
public interface StudentMapper {
    // 验证登录信息是否正确
    Student login(LoginForm loginForm);
    // 根据班级与学生名获取指定或全部学生信息列表
    List<Student> selectList(Student student);
    // 根据学号获取指定学生信息
    Student fingBySno(Student student);
    // 添加班级信息
    int insert(Student student);
    // 根据id修改指定学生信息
    int update(Student student);
    // 根据id修改指定学生密码
    int updatePassowrd(Student student);
    // TODO: 6/17/2019 根据id删除指定学生信息
    int deleteById(Integer[] ids);
}
