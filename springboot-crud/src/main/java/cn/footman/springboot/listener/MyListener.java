package cn.footman.springboot.listener;



import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author footman77
 * @create 2018-09-22 18:04
 */
public class MyListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("listener启动");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("listener销毁");
    }
}
