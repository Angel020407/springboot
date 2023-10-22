package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.Params;
import com.example.demo.entity.User;
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
}

