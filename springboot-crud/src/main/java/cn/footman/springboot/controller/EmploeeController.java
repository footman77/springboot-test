package cn.footman.springboot.controller;

import cn.footman.springboot.dao.DepartmentDao;
import cn.footman.springboot.dao.EmployeeDao;
import cn.footman.springboot.entities.Department;
import cn.footman.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @author footman77
 * @create 2018-09-20 15:30
 */
@Controller
public class EmploeeController {


    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;

    @GetMapping("/emps")
    public String list(ModelMap map){
        Collection<Employee> employees = employeeDao.getAll();
        map.addAttribute("emps",employees);
        return "emp/list";
    }

    @GetMapping("/emp")
    public String toAddPage(ModelMap map){

        //查出所有部门
        Collection<Department> departments = departmentDao.getDepartments();
        map.addAttribute("departments",departments);
        return "emp/add";
    }

    //添加
    @PostMapping("/emp")
    public String addEmployee(Employee employee){
        System.out.println(employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    //来到修改页面
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable() Integer id,ModelMap map){
        Employee employee = employeeDao.get(id);
        map.addAttribute("employee",employee);

        //查出所有部门
        Collection<Department> departments = departmentDao.getDepartments();
        map.addAttribute("departments",departments);
//        修改和添加二合一
        return "emp/add";

    }


    //修改内容
    @PutMapping("/emp")
    public String updateEmployee(Employee employee){
        System.out.println(employee);
        employeeDao.save(employee);


        return "redirect:/emps";
    }


    //删除员工
    @DeleteMapping("/emp/{id}")
    public String deleteEmp(@PathVariable() Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }

}
