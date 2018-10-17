package cn.footman.springbootcache.config;

import cn.footman.springbootcache.bean.Department;
import cn.footman.springbootcache.bean.Employee;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.net.UnknownHostException;

/**
 * @author footman77
 * @create 2018-10-14 14:48
 */
@Configuration
public class MyRedisConfig {

    @Bean
    public RedisTemplate<Object, Employee> EmpredisTemplate(
            RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        RedisTemplate<Object, Employee> template = new RedisTemplate<Object, Employee>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Employee> serializer = new Jackson2JsonRedisSerializer<Employee>(Employee.class);
        template.setDefaultSerializer(serializer);
        return template;
    }



    @Bean
    public RedisTemplate<Object, Department> deptredisTemplate(
            RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        RedisTemplate<Object, Department> template = new RedisTemplate<Object, Department>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Department> serializer = new Jackson2JsonRedisSerializer<Department>(Department.class);
        template.setDefaultSerializer(serializer);
        return template;
    }


    //CacheManagerCustomizers 可以来定制缓存的规则
    @Primary//将某个缓存管理器作为默认的使用
    @Bean
    public RedisCacheManager employeeCacheManager(RedisTemplate<Object, Employee> empredisTemplate){
        RedisCacheManager cacheManager = new RedisCacheManager(empredisTemplate);
        //key多了一个前缀
        //使用前缀，默认会将CacheName作为前缀
        cacheManager.setUsePrefix(true);
        return cacheManager;
    }

    @Bean
    public RedisCacheManager deptCacheManager(RedisTemplate<Object, Department> deptredisTemplate){
        RedisCacheManager cacheManager = new RedisCacheManager(deptredisTemplate);
        //key多了一个前缀
        //使用前缀，默认会将CacheName作为前缀
        cacheManager.setUsePrefix(true);
        return cacheManager;
    }
}
