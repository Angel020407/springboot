package com.example.demo.controller;

import com.example.demo.common.Result;
import com.example.demo.entity.Params;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@CrossOrigin//解决跨域问题
@RestController
@RequestMapping("/admin")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/login")

    public Result login(@RequestBody User user) {
        User loginUser = userService.login(user);  // loginUser -> admin 123456 *** ***
        Result tmp = Result.success(loginUser);
        return tmp;
    }

    @PostMapping("/register")

    public Result register(@RequestBody User user) {
        userService.add(user);  // loginUser -> admin 123456 *** ***
        return Result.success();
    }

    @PostMapping("/index")
    public Result save(@RequestBody User user) {
        if (user.getId() == null) {
            userService.add(user);
        } else {
            userService.update(user);
        }
        return Result.success();
    }
    @GetMapping
    public Result findAll()
    {
        List<User> list = userService.findAll();
        return Result.success(list);
    }

    @GetMapping("/search")
    public Result findBySearch(Params params) {
        PageInfo<User> info = userService.findBySearch(params);
        return Result.success(info);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        userService.delete(id);
        return Result.success();
    }
}
