package cn.footman.springbootcache.mapper;

import cn.footman.springbootcache.bean.Employee;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author footman77
 * @create 2018-09-26 15:13
 */
public interface EmployeeMapper {

    @Select("select * from employee where id = #{id}")
    public Employee getEmpById(Integer id);

    @Update("update employee set lastName=#{lastName},email=#{email},gender=#{gender},d_id=#{dId} where id = #{id}")
    public void updateEmp(Employee employee);

    @Delete("delete from employee where id = #{id}")
    public void deleteEmp(Integer id);


    @Insert("insert into employee (lastName,email,gender,d_id) values(#{lastName},#{email},#{gender},#{dId})")
    public void insertEmp(Employee employee);

    @Select("select * from employee where lastName = #{lastName}")
    Employee getEmpByLastName(String lastName);
}
