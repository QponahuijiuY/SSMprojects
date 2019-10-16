package com.Study.service;

import com.Study.bean.Clazz;

import java.util.List;

/**
 * @description: 操作班级信息
 * @Author: Mutong
 * @Date: 2019.10.13 11:29
 */
public interface ClazzService {
    //根据年级与班级查询指定/全部班级信息列表
    List<Clazz> selectList(Clazz clazz);
    //查询所有班级列表
    List<Clazz> selectAll();
    //添加班级信息
    int insert(Clazz clazz);
    //根据id删除指定班级信息
    int deleteById(Integer[] ids);
    //根据班级名字查询指定班级信息
    Clazz findByName(String name);
    //根据班级名字修改班级信息
    int update(Clazz clazz);

}
