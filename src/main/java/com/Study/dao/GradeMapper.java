package com.Study.dao;

import com.Study.bean.Grade;

import java.util.List;

/**
 * @description:
 * @Author: Mutong
 * @Date: 2019.10.13 13:39
 */
public interface GradeMapper {
    //根据年级名称查询指定年级列表信息
    List<Grade> selectList(Grade gradename);
    //查询所有年级列表信息
    List<Grade> selectAll();
    //根据年级名称查询指定年级信息
    Grade findByName(String gradename);
    //添加年级信息
    int insert(Grade grade);
    //根据id修改指定年级信息
    int update(Grade grade);
    //根据id删除指定年级信息
    int deleteById(Integer[] ids);
}
