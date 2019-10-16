package com.Study.controller;

import com.Study.bean.Admin;
import com.Study.bean.Student;
import com.Study.bean.Teacher;
import com.Study.service.AdminService;
import com.Study.service.StudentService;
import com.Study.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @Author: Mutong
 * @Date: 2019.10.11 17:43
 */

@Controller
@RequestMapping("/common")
public class CommonController {


    //注入业务对象
    @Autowired
    private AdminService adminService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;

    //存储预返回给页面的结果对象
    private Map<String, Object> result = new HashMap<>();

    @GetMapping("/goSettingView")
    public String getAdminList() {
        return "common/settings";
    }

    @PostMapping("/editPassword")
    @ResponseBody
    public Map<String, Object> editPassword(String oldPassword, String newPassword, HttpServletRequest request) {
        //判断当前登录用户的用户类型
        int userType = Integer.parseInt(request.getSession().getAttribute("userType").toString());
        //管理员身份
        if (userType == 1) {
            Admin admin = (Admin) request.getSession().getAttribute("userInfo");
            if (!admin.getPassword().equals(oldPassword)) {
                result.put("success", false);
                result.put("msg", "原密码错误!");
                return result;
            }
            try {
                //修改密码
                admin.setPassword(newPassword);//覆盖旧密码值,存储待更新的密码
                if (adminService.updatePassowrd(admin) > 0) {
                    result.put("success", true);
                }
            } catch (Exception e) {
                e.printStackTrace();
                result.put("success", false);
                result.put("msg", "修改失败! 服务器端发生异常!");
            }
        }
        //学生身份
        if (userType == 2) {
            Student student = (Student) request.getSession().getAttribute("userInfo");
            if (!student.getPassword().equals(oldPassword)) {
                result.put("success", false);
                result.put("msg", "原密码错误!");
                return result;
            }
            try {
                student.setPassword(newPassword);
                if (studentService.updatePassowrd(student) > 0) {
                    result.put("success", true);
                }
            } catch (Exception e) {
                e.printStackTrace();
                result.put("success", false);
                result.put("msg", "修改失败! 服务器端发生异常!");
            }
        }

        //教师身份
        if (userType == 3) {
            Teacher teacher = (Teacher) request.getSession().getAttribute("userInfo");
            if (!teacher.getPassword().equals(oldPassword)) {
                result.put("success", false);
                result.put("msg", "原密码错误!");
                return result;
            }
            try {
                teacher.setPassword(newPassword);
                if (teacherService.updatePassowrd(teacher) > 0) {
                    result.put("success", true);
                }
            } catch (Exception e) {
                e.printStackTrace();
                result.put("success", false);
                result.put("msg", "修改失败! 服务器端发生异常!");
            }
        }

        return result;
    }
}

