package cn.footman.springbootjpa.controller;

import cn.footman.springbootjpa.entity.User;
import cn.footman.springbootjpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author footman77
 * @create 2018-09-25 14:29
 */
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/user/{id}")
    public User getUser(@PathVariable() Integer id){
        User user = userRepository.findOne(id);
        return user;
    }
}
