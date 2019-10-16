package com.Study.service.impl;

import com.Study.bean.LoginForm;
import com.Study.bean.Student;
import com.Study.dao.StudentMapper;
import com.Study.service.StudentService;
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
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student login(LoginForm loginForm){
        return studentMapper.login(loginForm);
    }

    @Override
    public List<Student> selectList(Student student) {
        return studentMapper.selectList(student);
    }

    @Override
    public Student fingBySno(Student student) {
        return studentMapper.fingBySno(student);
    }

    @Override
    public int insert(Student student) {
        return studentMapper.insert(student);
    }

    @Override
    public int update(Student student) {
        return studentMapper.update(student);
    }

    @Override
    public int updatePassowrd(Student student) {
        return studentMapper.updatePassowrd(student);
    }

    @Override
    public int deleteById(Integer[] ids) {
        return studentMapper.deleteById(ids);
    }
}
