package cn.footman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author footman77
 * @create 2018-09-18 15:44
 */
@SpringBootApplication
public class HelloWorldMainApplication {

    public static void main(String[] args) {
        //启动spring应用
        SpringApplication.run(HelloWorldMainApplication.class,args);
    }
}
