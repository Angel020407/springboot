package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.Params;
import com.example.demo.entity.User;
import com.example.demo.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

    @Resource
    private UserDao userDao;
    public List<User> findAll(){

        return userDao.selectAll();
    }

    public PageInfo<User> findBySearch(Params params) {
        // 开启分页查询
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        // 接下来的查询会自动按照当前开启的分页设置来查询
        List<User> list = userDao.findBySearch(params);
        return PageInfo.of(list);
    }

    public void add(User user) {
        if(user.getName() == null || "".equals(user.getName())) {
            throw new CustomException("当前用户名为空！");
        }
        //可用于学号的不可重复添加
        User admin = userDao.findByName(user.getName());
        if(admin != null) {
            //说明已经有了，不可重复添加
            throw new CustomException("该用户名已存在，请勿重复添加");
        }
        // 初始化一个密码
        if (user.getPassword() == null) {
            user.setPassword("123456");
        }
        userDao.insertSelective(user);
    }

    public void update(User user) {
        userDao.updateByPrimaryKeySelective(user);
    }

    public void delete(Integer id) {
        userDao.deleteByPrimaryKey(id);
    }

    public User login(User user) {
        //判断数据是否为空
        if (user.getName() == null || "".equals(user.getName())) {
            throw new CustomException("用户名不能为空");
        }
        if (user.getPassword() == null || "".equals(user.getPassword())) {
            throw new CustomException("密码不能为空");
        }
        //遍历数据库匹配用户输入,存入临时容器
        User admin = userDao.findByNameAndPassword(user.getName(), user.getPassword());
        if (admin == null) {
            // 用户名或者密码有误，登录失败
            throw new CustomException("用户名或密码输入错误");
        }
        return admin;
    }
}

