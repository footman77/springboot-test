package cn.footman.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author footman77
 * @create 2018-09-18 17:53
 */
@Controller
public class HelloController {

    @RequestMapping("hello")
    @ResponseBody
    public String hello(){
        return "hello world";
    }

}
