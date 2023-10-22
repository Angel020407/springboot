package com.example.demo.controller;

import com.example.demo.common.Result;
import com.example.demo.entity.Params;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@CrossOrigin//解决跨域问题
@RestController
@RequestMapping("/admin")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping
    public Result findAll()
    {
        List<User> list = userService.findAll();
        return Result.success(list);
    }

    @GetMapping("/search")
    public Result findBySearch(Params params) {
        List<User> list = userService.findBySearch(params);
        return Result.success(list);
    }
}
