package com.Study.service.impl;

import com.Study.bean.Clazz;
import com.Study.dao.ClazzMapper;
import com.Study.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description:
 * @Author: Mutong
 * @Date: 2019.10.13 11:35
 */
@Service
@Transactional
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;

    @Override
    public List<Clazz> selectList(Clazz clazz) {
        return clazzMapper.selectList(clazz);
    }

    @Override
    public List<Clazz> selectAll() {
        return clazzMapper.selectAll();
    }

    @Override
    public int insert(Clazz clazz) {
        return clazzMapper.insert(clazz);
    }

    @Override
    public int deleteById(Integer[] ids) {
        return clazzMapper.deleteById(ids);
    }

    @Override
    public Clazz findByName(String name) {
        return clazzMapper.findByName(name);
    }

    @Override
    public int update(Clazz clazz) {
        return clazzMapper.update(clazz);
    }
}
