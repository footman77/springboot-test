package cn.footman.springboot.config;

import cn.footman.springboot.filter.MyFilter;
import cn.footman.springboot.listener.MyListener;
import cn.footman.springboot.servlet.Myservlet;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @author footman77
 * @create 2018-09-22 17:52
 */
@Configuration
public class MyServerConfig {


    //注册三大组件
    @Bean
    public ServletRegistrationBean myServelt(){
       return new ServletRegistrationBean(new Myservlet(),"/myServlet");
    }


    @Bean
    public FilterRegistrationBean myFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new MyFilter());
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/hello","/myServlet"));
        return filterRegistrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean myListener(){
        ServletListenerRegistrationBean<MyListener> listenerRegistrationBean = new ServletListenerRegistrationBean<>(new MyListener());
        return listenerRegistrationBean;

    }

    //配置服务器
    @Bean
    public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer(){
        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
                configurableEmbeddedServletContainer.setPort(8083);
            }
        };
    }

}
