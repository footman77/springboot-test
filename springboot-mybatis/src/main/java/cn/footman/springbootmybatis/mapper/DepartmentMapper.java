package cn.footman.springbootmybatis.mapper;

import cn.footman.springbootmybatis.bean.Department;
import org.apache.ibatis.annotations.*;

/**
 * @author footman77
 * @create 2018-09-25 10:46
 */
//@Mapper
public interface DepartmentMapper {
    @Select("select * from department where id = #{id}")
    public Department getDepartmentById(Integer id);

    @Delete("delete from department where id = #{id}")
    public int deleteDepartmentById(Integer id);

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into department (departmentName) values (#{departmentName})")
    public int insertDepartment(Department department);

    @Update("update department set departmentName=#{departmentName} where id=#{id}")
    public int updateDepartment(Department department);
}
