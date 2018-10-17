package cn.footman.springbootcache.service;

import cn.footman.springbootcache.bean.Employee;
import cn.footman.springbootcache.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

/**
 * @author footman77
 * @create 2018-09-26 15:22
 */
@CacheConfig(cacheNames = "emp",cacheManager = "employeeCacheManager") //抽取缓存的公共配置
@Service
public class EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;


    /**
     * CacheManager管理多个Cache嘴贱，对缓存的真正CRUD操作在Cache组件中，每一个缓存组件有自己唯一一个名字；
     * 几个属性
     *      chcheNames/value：指定缓存的名字；将方法的返回结果放到哪个缓存中，是数组的方式，可以指定多个缓存
     *      key：缓存数据时用的key，可以用他来指定，默认是方法参数的值，对应的value是方法的返回值
     *              编写SpEL；#id;参数id的值  #a0  #p0  #root.args[0]
     *              #root.methodName + '[' +#id + ']'
     *      keyGenerator:key的生成器；可以自己指定key的生成器
     *              key/keyGenerator二选一使用
     *      cacheManager：指定缓存管理器；或者cacheResolver指定获得解析器
     *      condition：指定符合条件的情况下才缓存
     *          condition = "#a0 > 1":第一个参数的值大于1，才进行缓存
     *      unless：否定缓存；当unless指定的条件为true，方法的返回值不会被缓存
     *              unless="#result== null"
     *      sync:是否使用异步模式
     *
     * 原理：
     *  1、自动配置类  CacheAutoConfiguration
     *  2、缓存的配置类
     *      org.springframework.boot.autoconfigure.cache.GenericCacheConfiguration
     *      org.springframework.boot.autoconfigure.cache.JCacheCacheConfiguration
     *      org.springframework.boot.autoconfigure.cache.EhCacheCacheConfiguration
     *      org.springframework.boot.autoconfigure.cache.HazelcastCacheConfiguration
     *      org.springframework.boot.autoconfigure.cache.InfinispanCacheConfiguration
     *      org.springframework.boot.autoconfigure.cache.CouchbaseCacheConfiguration
     *      org.springframework.boot.autoconfigure.cache.RedisCacheConfiguration
     *      org.springframework.boot.autoconfigure.cache.CaffeineCacheConfiguration
     *      org.springframework.boot.autoconfigure.cache.GuavaCacheConfiguration
     *      org.springframework.boot.autoconfigure.cache.SimpleCacheConfiguration
     *      org.springframework.boot.autoconfigure.cache.NoOpCacheConfiguration
     *  3、哪个配置类默认生效
     *      SimpleCacheConfiguration,
     *  4、给容器中注册一个cachemanager：ConcurrentMapCacheManager
     *  5、可以获得和创建ConcurrentMapCache类型的缓存组件；将数据保存在ConcurrentMap中
     *
     *  运行流程
     * @Cacheable
     *  1、方法运行之前，先去查询Cache缓存组件，按照cacheNames指定的名字获取
     *  （cacheManager先获取相应的缓存），第一次获取缓存如果没有cache组件会自动创建出来
     *  2、去cache中查找缓存的内容，使用一个key，默认就是方法的参数
     *      key是按照魔种策略生成的，默认是使用keyGenerator生成的，默认使用simplekeyGenerator生成
     *          simpleKeyGenerator的生成key策略
     *              如果没有参数，则key = new Simplekey();
     *              如果有一个参数，则key=这个参数的值
     *              如果有多个参数，则key = new Simplekey(params);
     *  3、没有查到缓存就调用目标方法
     *  4、将目标方法返回的结果，放到缓存中
     *
     * @Cacheable 标注的方法执行之前先来检查缓存中有么有这个数据，默认按照参数的值作为key去查找，如果没有就
     * 运行方法，并将结果放入缓存
     *
     * 核心：
     *  1、使用CacheManager【ConcurrentMapCacheManager】按照名字得到Cache【ConcurrentMapCache】组件
     *  2、key使用keyGenerator生成的，默认是SimpleKeyGenerator
     *
     * @param id
     * @return
     */

    @Cacheable(cacheNames = "emp"/*,keyGenerator = "myKeyGenerator"*/)
    public Employee getEmpById(Integer id){
        System.out.println("查询" + id + "员工");
        Employee emp = employeeMapper.getEmpById(id);
        return emp;
    }


    /**
     * @CachePut: 既调用方法，又更新缓存;同步更新缓存
     * 修改了数据库的某个数据，同时更新缓存
     * 运行时机：
     *  1、先调用目标方法
     *  2、将目标方法的返回值放入缓存
     *
     * 测试步骤：
     * 1、查询1号员工：查到的结果会放到缓存中
     *      key：1 value： lastName为张三的用户信息
     * 2、以后查询还是之前的结果
     * 3、更新1号员工
     *      将方法的返回值放进缓存
     *          key：传入的employee对象， 值：返回的employee对象
     * 4、查询1号员工？
     *      应该是更新后的员工
     *         key = "#employee.id":使用传入的参数的员工id
     *         key = "#result.id":使用返回后的id
     * @Cacheable 的key是不能用#result的
     *  由于key的不同，没有更新之前在缓存中的员工信息
     */
    @CachePut(/*value = "emp",*/key = "#result.id")
    public Employee upadateEmp(Employee employee){
        System.out.println("updateEmp:" + employee);
        employeeMapper.updateEmp(employee);
        return employee;
    }

    /**
     * @CacheEvict :清除缓存
     * key:指定要清除的数据
     *  allEntries = true 清除缓存中所有的数据
     *  beforeInvocation = false : 清除缓存是否在方法之前执行
     *      默认在方法之后执行 ：方法出错，那么缓存就不会被清除
     *  beforeInvocation = true
     *      方法之前就执行：即表示不管方法是否出错，都清除缓存
     *
     *
     *
     */
    @CacheEvict(value = "emp",key = "#id"/*, allEntries = true*/)
    public void deleteEmp(Integer id){
        System.out.println("deleteEmp" + id);
//        employeeMapper.deleteEmp(id);
    }



    /*
    @Caching 定义复杂的缓存规则

     */
    @Caching(
            cacheable = {
                    @Cacheable(/*value = "emp" ,*/key = "#lastName")
            },
            put = {
                    @CachePut(/*value = "emp" ,*/ key = "#result.id"),
                    @CachePut(/*value = "emp" ,*/ key = "#result.email")
            }
    )
    public Employee getEmpByLastName(String lastName){
        return employeeMapper.getEmpByLastName(lastName);
    }
}
