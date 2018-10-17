package cn.footman.springbootcache.controller;

import cn.footman.springbootcache.bean.Department;
import cn.footman.springbootcache.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author footman77
 * @create 2018-10-14 15:33
 */
@RestController
public class DeptController {


    @Autowired
    DeptService deptService;

    @GetMapping("/dept/{id}")
    public Department getDept(@PathVariable() Integer id){
        Department dept = deptService.getDeptById(id);
        return dept;
    }
}
