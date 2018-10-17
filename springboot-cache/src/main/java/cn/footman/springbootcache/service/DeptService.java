package cn.footman.springbootcache.service;

import cn.footman.springbootcache.bean.Department;
import cn.footman.springbootcache.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;

/**
 * @author footman77
 * @create 2018-10-14 15:28
 */
@CacheConfig(cacheManager = "deptCacheManager")
@Service
public class DeptService {

    @Autowired
    DepartmentMapper departmentMapper;

    @Qualifier("deptCacheManager")
    @Autowired
    RedisCacheManager deptCacheManager;

    /**
     * 缓存的数据能存入redis
     * 第二次从缓存中查询就不能反序列化
     * 存的是dept的json数据；CacheManager默认使用RedisTemplate<Object,Employee>操作redis
     *
     * @param id
     * @return
     */
//    @Cacheable(value = "dept")
//    public Department getDeptById(Integer id){
//        System.out.println("查询部门" + id);
//        Department dept = departmentMapper.getDeptById(id);
//        return dept;
//    }

//    @Cacheable(value = "dept")
    //直接使用缓存管理器，得到缓存操作
    public Department getDeptById(Integer id){
        System.out.println("查询部门" + id);
        Department dept = departmentMapper.getDeptById(id);
        //获取某个缓存
        Cache cache = deptCacheManager.getCache("dept");
        cache.put("dept:1",dept);
        return dept;
    }
}
