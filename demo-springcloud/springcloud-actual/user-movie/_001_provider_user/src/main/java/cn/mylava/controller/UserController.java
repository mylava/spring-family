package cn.mylava.controller;

import cn.mylava.dao.UserDao;
import cn.mylava.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * comment:
 *
 * @author: lipengfei
 * @date: 08/10/2018
 * @company: (C) Copyright 58BTC 2018
 * @since: JDK 1.8
 * @description:
 */
@RestController
public class UserController {
    @Autowired
    private UserDao userDao;

    @GetMapping("/user/{id}")
    public User findById(@PathVariable Long id) {
        return userDao.getOne(id);
    }

}
