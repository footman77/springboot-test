package cn.footman.springboot.config;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author footman77
 * @create 2018-09-20 13:33
 */
@Controller
public class LoginController {

//    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    @PostMapping(value = "/user/login")
    public String login(String username, String password, Map<String,Object> map, HttpSession session){
        if(!StringUtils.isEmpty(username) && "123456".equals(password)){
//            登陆成功，将用户名和密码存入session中
            session.setAttribute("username",username);
//            session.setAttribute("password",password);
            //登陆成功,防止表单重复提交，需要重定向
            return "redirect:/main.html";

        }else {
            //登陆失败
            map.put("msg","用户名或密码错误");
            return "login";
        }
    }


}
