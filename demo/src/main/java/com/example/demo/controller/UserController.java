package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/strat")
    public String start()
    {
        return "欢迎";
    }

    @GetMapping("/getUser")
    public User getUser()
    {
        return new User("张三", "男", 14, "18266668888");
    }
}
