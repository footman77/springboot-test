package cn.footman.springbootcache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 *
 * 1.开启基于注解的缓存@EnableCaching
 * 2.标注缓存注解
 * @Cacheable 主要针对方法配置，能够根据方法的请求参数对其结果进行缓存
 * @CacheEvict 清空缓存
 * @CachePut 保证方法被调用，又希望结果被缓存。
 *
 * 默认使用的是ConcurrentMapCacheManager的CacheManager
 * 获得和创建ConcurrentMapCache类型的缓存组件；将数据保存在ConcurrentMap中
 * 开发中使用缓存中间件：redis、memcached、ehcache
 *
 * 整合redis作为缓存
 * Redis是一个开源（BSD许可），内存存储的数据结构服务器，可用作数据库，高速缓存和消息队列代理
 *  1、安装redis
 *  2、引入redis的starter
 *  3、配置redis
 *  4、测试缓存
 *      原理：CacheManager---->Cache缓存组件来实际给缓存中存取数据
 *      1)、引入redis的starter，容器中保存的是RedisCacheManager
 *      2)、RedisCacheManager帮我们创建RedisCache作为缓存组件；RedisCache通过操作redis缓存数据的
 *      3)、默认保存数据k-v都是Object，利用序列化保存
 *          1、引入redis的starter，cacheManager变为RedisCacheManager
 *          2、默认创建的RedisCacheManager 操作redis的时候传入RedisTemplate<Object, Object>
 *          3、RedisTemplate<Object, Object>是默认使用jdk的序列化机制
 *      4)、自定义CacheManager
 *
 *
 */

@MapperScan(basePackages = "cn.footman.springbootcache.mapper")
@SpringBootApplication
@EnableCaching
public class SpringbootCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootCacheApplication.class, args);
    }
}
