package cn.footman.task.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author footman77
 * @create 2018-10-15 19:32
 */
@Service
public class ScheduledService {

    //0 * * * * MON-FRI
//    @Scheduled(cron = "0 * * * * MON-FRI")
    public void hello(){
        System.out.println("henhao ");
    }

}
