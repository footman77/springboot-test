package cn.footman.springbootcache.controller;

import cn.footman.springbootcache.bean.Employee;
import cn.footman.springbootcache.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author footman77
 * @create 2018-09-26 15:23
 */

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @GetMapping("/emp/{id}")
    public Employee getEmp(@PathVariable() Integer id){
        Employee emp = employeeService.getEmpById(id);
        return emp;
    }
    @GetMapping("/emp")
    public Employee update(Employee employee){
        Employee emp = employeeService.upadateEmp(employee);

        return emp;
    }
    @GetMapping("/delemp")
    public String  delete(Integer id){
        employeeService.deleteEmp(id);
        return "success";
    }

    @GetMapping("/emp/lastName/{lastName}")
    public Employee getEmpByLastName(@PathVariable() String lastName){
        return employeeService.getEmpByLastName(lastName);
    }
}
