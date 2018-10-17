package cn.footman.springbootmybatis.controller;

import cn.footman.springbootmybatis.bean.Department;
import cn.footman.springbootmybatis.bean.Employee;
import cn.footman.springbootmybatis.mapper.DepartmentMapper;
import cn.footman.springbootmybatis.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author footman77
 * @create 2018-09-25 10:57
 */
@RestController
public class DepController {


    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @GetMapping("/dept/{id}")
    public Department getDepartment(@PathVariable() Integer id){
        return departmentMapper.getDepartmentById(id);
    }
    @GetMapping("/dept")
    public Department insertDepartment(Department department){
        departmentMapper.insertDepartment(department);
        return department;
    }

    @GetMapping("/emp/{id}")
    public Employee getEmployee(@PathVariable() Integer id){
        return employeeMapper.getEmployeeById(id);
    }




}
