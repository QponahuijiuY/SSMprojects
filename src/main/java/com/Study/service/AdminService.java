package com.Study.service;

import com.Study.bean.Admin;
import com.Study.bean.LoginForm;

import java.util.List;

/**
 * @description:
 * @Author: Mutong
 * @Date: 2019.10.13 11:29
 */
public interface AdminService {
    //验证登陆信息是否正确
    Admin login(LoginForm loginForm);
    //根据用户名查询指定管理员
    Admin findByName(String name);
    //添加管理员信息
    int insert(Admin admin);
    //根据姓名查询指定/所有管理员信息列表
    List<Admin> selectList(Admin admin);
    //更新指定管理员的信息
    int update(Admin admin);
    //修改指定管理员密码
    int updatePassowrd(Admin admin);
    //删除管理员信息
    int deleteById(Integer[] ids);

}
