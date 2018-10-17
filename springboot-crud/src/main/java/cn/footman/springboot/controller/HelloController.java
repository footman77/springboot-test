package cn.footman.springboot.controller;

import cn.footman.springboot.exception.UserNotExistException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author footman77
 * @create 2018-09-19 21:36
 */
@Controller
public class HelloController {

    @ResponseBody
    @RequestMapping("/hi")
    public String hi(String user){
        if("a".equals(user)){
            throw new UserNotExistException();
        }
        return "hello world!";

    }

    @RequestMapping("hello")
    public String hello(ModelMap map){

        map.addAttribute("name","zhangsan");
        return "success";
    }

    @RequestMapping("test")
    public String test(Map<String,Object> map){
//        map.put("name","lisi");
        map.get("name");
        System.out.println(map.get("name"));
        map.put("name","lisi");
        return "thymeleaf/test";
    }
}
