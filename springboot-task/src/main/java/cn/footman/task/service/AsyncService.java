package cn.footman.task.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author footman77
 * @create 2018-10-15 19:20
 */
@Service
public class AsyncService {

    //告诉spring这是一个异步方法
    @Async
    public void hello(){
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("hello world!!");
    }
}
