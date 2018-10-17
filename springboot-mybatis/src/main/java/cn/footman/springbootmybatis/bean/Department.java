package cn.footman.springbootmybatis.bean;

/**
 * @author footman77
 * @create 2018-09-24 17:54
 */
public class Department {


    private Integer id;
    private String departmentName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
