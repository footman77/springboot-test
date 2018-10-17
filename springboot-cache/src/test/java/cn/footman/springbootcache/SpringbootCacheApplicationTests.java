package cn.footman.springbootcache;

import cn.footman.springbootcache.bean.Employee;
import cn.footman.springbootcache.mapper.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootCacheApplicationTests {


    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    RedisTemplate redisTemplate;//k-v都是对象
    @Autowired
    StringRedisTemplate stringRedisTemplate;//操作k-v都是字符串

    @Autowired
    RedisTemplate<Object, Employee> employeeRedisTemplate;

    @Test
    public void contextLoads() {

        Employee emp = employeeMapper.getEmpById(1);

        System.out.println(emp);
    }


    /**
     * redis常见的五大数据类型
     * String(字符串)、List(列表)、Set(集合)、Hash(散列)、SZet(有序集合)
     * stringRedisTemplate.opsForValue()[String(字符串)]
     * stringRedisTemplate.opsForList()[List(列表)]
     * stringRedisTemplate.opsForSet()[Set(集合)]
     * stringRedisTemplate.opsForHash()[Hash(散列)]
     * stringRedisTemplate.opsForZSet()[SZet(有序集合)]
     */
    @Test
    public void test01(){
        //保存数据
//        stringRedisTemplate.opsForValue().append("msg","world");
        String msg = stringRedisTemplate.opsForValue().get("msg");
        System.out.println(msg);
    }

    /**
     * 保存对象
     *
     */
    @Test
    public void test02(){
        Employee emp = employeeMapper.getEmpById(1);
        //默认如果保存对象，使用jdk序列化机制，序列化后的数据保存到redis中
        //1、将数据以json的方式保存
        //（1）自己将对象转化为json
        //（2）redisTemplate默认的序列化规则;改变默认的序列化规则

//        redisTemplate.opsForValue().set("emp01",emp);
        employeeRedisTemplate.opsForValue().set("emp01",emp);
    }


}
