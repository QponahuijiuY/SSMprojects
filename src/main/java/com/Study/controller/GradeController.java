package com.Study.controller;

import com.Study.bean.Grade;
import com.Study.service.GradeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @Author: Mutong
 * @Date: 2019.10.11 17:44
 */

@Controller
@RequestMapping("/grade")
public class GradeController {

    // 注入业务对象
    @Autowired
    private GradeService gradeService;

    // 存储预返回页面的结果对象
    private Map<String, Object> result = new HashMap<>();

    @GetMapping("/goGradeListView")
    public String goGradeListPage() {
        return "grade/gradeList";
    }

    @PostMapping("/getGradeList")
    @ResponseBody
    public Map<String, Object> getGradeList(Integer page, Integer rows, String gradename) {

        //注意:使用Java Bean传递gradename,防止以下异常 !
        //org.springframework.web.util.NestedServletException: Request processing failed;
        // nested exception is org.mybatis.spring.MyBatisSystemException:
        // nested exception is org.apache.ibatis.reflection.ReflectionException:
        // There is no getter for property named 'name' in 'class java.lang.String'
        Grade grade = new Grade();
        grade.setName(gradename);

        //设置每页的记录数
        PageHelper.startPage(page, rows);
        //根据年级名称获取指定或全部年级信息列表
        List<Grade> list = gradeService.selectList(grade);
        //封装信息列表
        PageInfo<Grade> pageInfo = new PageInfo<>(list);
        //获取总记录数
        long total = pageInfo.getTotal();
        //获取当前页数据列表
        List<Grade> gradeList = pageInfo.getList();
        //存储数据对象
        result.put("total", total);
        result.put("rows", gradeList);

        return result;
    }

    @PostMapping("/addGrade")
    @ResponseBody
    public Map<String, Object> addGrade(Grade grade) {
        //判断年级名是否已存在
        Grade name = gradeService.findByName(grade.getName());
        if (name == null) {
            if (gradeService.insert(grade) > 0) {
                result.put("success", true);
            } else {
                result.put("success", false);
                result.put("msg", "添加失败! (ಥ_ಥ)服务器端发生异常!");
            }
        } else {
            result.put("success", false);
            result.put("msg", "该年级名称已存在! 请修改后重试!");
        }

        return result;
    }

    @PostMapping("/editGrade")
    @ResponseBody
    public Map<String, Object> editGrade(Grade grade) {
        //需排除用户只修改年级名以外的信息
        Grade g = gradeService.findByName(grade.getName());
        if (g != null) {
            if (!(grade.getId().equals(g.getId()))) {
                result.put("success", false);
                result.put("msg", "该年级名称已存在! 请修改后重试!");
                return result;
            }
        }
        //添加操作
        if (gradeService.update(grade) > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
            result.put("msg", "添加失败! (ಥ_ಥ)服务器端发生异常!");
        }
        return result;
    }

    @PostMapping("/deleteGrade")
    @ResponseBody
    public Map<String, Object> deleteGrade(@RequestParam(value = "ids[]", required = true) Integer[] ids) {

        if (gradeService.deleteById(ids) > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        return result;
    }

}
