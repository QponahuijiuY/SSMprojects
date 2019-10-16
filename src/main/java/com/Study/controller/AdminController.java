package com.Study.controller;

import com.Study.bean.Admin;
import com.Study.service.AdminService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 控制器-管理员信息页面
 * @Author: Mutong
 * @Date: 2019.10.11 17:42
 */

@Controller
@RequestMapping("/admin")
public class AdminController {

    //注入业务对象
    @Autowired
    private AdminService adminService;

    //存储预返回页面的结果对象
    private Map<String, Object> result = new HashMap<>();

    @GetMapping("/goAdminListView")
    public String getAdminList() {
        return "admin/adminList";
    }

    @PostMapping("/getAdminList")
    @ResponseBody
    public Map<String, Object> getAdminList(Integer page, Integer rows, String username) {

        //获取查询的用户名
        Admin admin = new Admin();
        admin.setName(username);
        //设置每页的记录数
        PageHelper.startPage(page, rows);
        //根据姓名获取指定或所有管理员列表信息
        List<Admin> list = adminService.selectList(admin);
        //封装查询结果
        PageInfo<Admin> pageInfo = new PageInfo<>(list);
        //获取总记录数
        long total = pageInfo.getTotal();
        //获取当前页数据列表
        List<Admin> adminList = pageInfo.getList();
        //存储对象数据
        result.put("total", total);
        result.put("rows", adminList);

        return result;
    }

    @PostMapping("/addAdmin")
    @ResponseBody
    public Map<String, Object> addAdmin(Admin admin) {
        //判断用户名是否已存在
        Admin user = adminService.findByName(admin.getName());
        if (user == null) {
            if (adminService.insert(admin) > 0) {
                result.put("success", true);
            } else {
                result.put("success", false);
                result.put("msg", "添加失败! (ಥ_ಥ)服务器端发生异常!");
            }
        } else {
            result.put("success", false);
            result.put("msg", "该用户名已存在! 请修改后重试!");
        }
        return result;
    }

    @PostMapping("/editAdmin")
    @ResponseBody
    public Map<String, Object> edityAdmin(Admin admin) {
        //需排除用户只修改账户名以外的信息
        Admin user = adminService.findByName(admin.getName());
        if (user != null) {
            if (!(admin.getId().equals(user.getId()))) {
                result.put("success", false);
                result.put("msg", "该用户名已存在! 请修改后重试!");
                return result;
            }
        }
        //添加操作
        if (adminService.update(admin) > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
            result.put("msg", "添加失败! (ಥ_ಥ)服务器端发生异常!");
        }
        return result;
    }

    @PostMapping("/deleteAdmin")
    @ResponseBody
    public Map<String, Object> deleteAdmin(@RequestParam(value = "ids[]", required = true) Integer[] ids) {

        if (adminService.deleteById(ids) > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        return result;
    }

}
