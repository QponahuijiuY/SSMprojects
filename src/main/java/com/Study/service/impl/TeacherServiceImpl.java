package com.Study.service.impl;

import com.Study.bean.LoginForm;
import com.Study.bean.Teacher;
import com.Study.dao.Teachermapper;
import com.Study.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description:
 * @Author: Mutong
 * @Date: 2019.10.13 11:36
 */

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private Teachermapper teachermapper;

    @Override
    public Teacher login(LoginForm loginForm) {
        return teachermapper.login(loginForm);
    }

    @Override
    public List<Teacher> selectList(Teacher teacher) {
        return teachermapper.selectList(teacher);
    }

    @Override
    public Teacher findByTno(Teacher teacher) {
        return teachermapper.findByTno(teacher);
    }

    @Override
    public int insert(Teacher teacher) {
        return teachermapper.insert(teacher);
    }

    @Override
    public int update(Teacher teacher) {
        return teachermapper.update(teacher);
    }

    @Override
    public int deleteById(Integer[] ids) {
        return teachermapper.deleteById(ids);
    }

    @Override
    public int updatePassowrd(Teacher teacher) {
        return teachermapper.updatePassowrd(teacher);
    }
}
