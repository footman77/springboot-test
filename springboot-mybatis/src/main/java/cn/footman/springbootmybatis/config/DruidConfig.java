package cn.footman.springbootmybatis.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author footman77
 * @create 2018-09-24 17:45
 */
@Configuration
public class DruidConfig {



    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid(){
        return new DruidDataSource();
    }


    //配置Druid监控
//    配置一个管理后台的servlet

    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String,String> map = new HashMap<String,String>();
        map.put("loginUsername","admin");
        map.put("loginPassword","admin");
        map.put("allow","");//不写默认全部允许
        map.put("deny","192.168.0.108");

        servletRegistrationBean.setInitParameters(map);
        return servletRegistrationBean;

    }

    //配置web监控的filter
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());

        Map<String,String> map = new HashMap<String,String>();
        map.put("exclusions","*.js,*.css,/druid/*");

        filterRegistrationBean.setInitParameters(map);

        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));

        return filterRegistrationBean;
    }

}
