package com.Study.controller;

import com.Study.bean.Clazz;
import com.Study.service.ClazzService;
import com.Study.service.GradeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @Author: Mutong
 * @Date: 2019.10.11 17:43
 */

@Controller
@RequestMapping("/clazz")
public class ClazzController {

    //注入业务对象
    @Autowired
    private ClazzService clazzService;
    @Autowired
    private GradeService gradeService;

    //存储预返回页面的数据对象
    private Map<String, Object> result = new HashMap<>();

    @GetMapping("/goClazzListView")
    public ModelAndView goClazzListPage(ModelAndView modelAndView) {
        //向页面发送一个存储着Grade的List对象
        modelAndView.addObject("gradeList", gradeService.selectAll());
        modelAndView.setViewName("clazz/clazzList");
        return modelAndView;
    }

    @PostMapping("/getClazzList")
    @ResponseBody
    public Map<String, Object> getClazzList(Integer page, Integer rows, String clazzname, String gradename) {

        //存储查询的clazzname,gradename信息
        Clazz clazz = new Clazz(clazzname, gradename);
        //设置每页的记录数
        PageHelper.startPage(page, rows);
        //根据班级与年级名获取指定或全部班级信息列表
        List<Clazz> list = clazzService.selectList(clazz);
        //封装列表信息
        PageInfo<Clazz> pageInfo = new PageInfo<>(list);
        //获取总记录数
        long total = pageInfo.getTotal();
        //获取当前页数据列表
        List<Clazz> clazzList = pageInfo.getList();
        //存储数据对象
        result.put("total", total);
        result.put("rows", clazzList);

        return result;
    }

    @PostMapping("/addClazz")
    @ResponseBody
    public Map<String, Object> addClazz(Clazz clazz) {
        //判断班级名是否已存在
        Clazz name = clazzService.findByName(clazz.getName());
        if (name == null) {
            if (clazzService.insert(clazz) > 0) {
                result.put("success", true);
            } else {
                result.put("success", false);
                result.put("msg", "添加失败! (ಥ_ಥ)服务器端发生异常!");
            }
        } else {
            result.put("success", false);
            result.put("msg", "该班级名称已存在! 请修改后重试!");
        }

        return result;
    }

    @PostMapping("/editClazz")
    @ResponseBody
    public Map<String, Object> editClazz(Clazz clazz) {
        //需排除用户只修改班级名以外的信息
        Clazz c = clazzService.findByName(clazz.getName());
        if (c != null) {
            if (!(clazz.getId().equals(c.getId()))) {
                result.put("success", false);
                result.put("msg", "该班级名称已存在! 请修改后重试!");
                return result;
            }
        }
        //添加操作
        if (clazzService.update(clazz) > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
            result.put("msg", "添加失败! (ಥ_ಥ)服务器端发生异常!");
        }
        return result;
    }

    @PostMapping("/deleteClazz")
    @ResponseBody
    public Map<String, Object> deleteGrade(@RequestParam(value = "ids[]", required = true) Integer[] ids) {

        if (clazzService.deleteById(ids) > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        return result;
    }
}
