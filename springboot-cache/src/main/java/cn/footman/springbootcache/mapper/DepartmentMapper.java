package cn.footman.springbootcache.mapper;

import cn.footman.springbootcache.bean.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author footman77
 * @create 2018-10-13 19:45
 */
@Mapper
public interface DepartmentMapper {
    @Select("select * from department where id = #{id}")
    Department getDeptById(Integer id);
}
