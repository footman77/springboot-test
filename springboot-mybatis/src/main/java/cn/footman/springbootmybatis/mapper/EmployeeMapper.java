package cn.footman.springbootmybatis.mapper;

import cn.footman.springbootmybatis.bean.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author footman77
 * @create 2018-09-25 13:03
 */
//@Mapper
public interface EmployeeMapper {

    public Employee getEmployeeById(Integer id);

    public void insertEmp(Employee employee);
}
