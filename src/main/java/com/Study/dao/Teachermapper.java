package com.Study.dao;

import com.Study.bean.LoginForm;
import com.Study.bean.Teacher;

import java.util.List;

/**
 * @description:
 * @Author: Mutong
 * @Date: 2019.10.13 13:40
 */
public interface Teachermapper {
    //  验证登录信息是否正确
    Teacher login(LoginForm loginForm);
    // 根据教师与班级名查询指定/全部教师列表信息
    List<Teacher> selectList(Teacher teacher);
    //  根据工号查询指定教师信息
    Teacher findByTno(Teacher teacher);
    //  添加教师信息
    int insert(Teacher teacher);
    // 根据id修改指定教师信息
    int update(Teacher teacher);
    // 根据id删除指定教师信息
    int deleteById(Integer[] ids);
    // 根据id修改指定教师密码
    int updatePassowrd(Teacher teacher);
}
