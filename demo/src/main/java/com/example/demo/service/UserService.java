package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.Params;
import com.example.demo.entity.User;
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

    public List<User> findBySearch(Params params) {
        return userDao.findBySearch(params);
    }
}

