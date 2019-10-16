package com.Study.service.impl;

import com.Study.bean.Admin;
import com.Study.bean.LoginForm;
import com.Study.dao.AdminMapper;
import com.Study.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description: 业务实现层-操作管理员信息
 * @Author: Mutong
 * @Date: 2019.10.13 11:34
 */

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    //注入Mapper接口对象
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin login(LoginForm loginForm) {
        return adminMapper.login(loginForm);
    }

    @Override
    public Admin findByName(String name) {
        return adminMapper.findByName(name);
    }

    @Override
    public int insert(Admin admin) {
        return adminMapper.insert(admin);
    }

    @Override
    public List<Admin> selectList(Admin admin) {
        return adminMapper.selectList(admin);
    }

    @Override
    public int update(Admin admin) {
        return adminMapper.update(admin);
    }

    @Override
    public int updatePassowrd(Admin admin) {
        return adminMapper.updatePassowrd(admin);
    }

    @Override
    public int deleteById(Integer[] ids) {
        return adminMapper.deleteById(ids);
    }
}
