package cn.mylava.controller;

import cn.mylava.UserFacadeClient;
import cn.mylava.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * comment:
 *
 * @author: lipengfei
 * @date: 10/10/2018
 * @company: (C) Copyright 58BTC 2018
 * @since: JDK 1.8
 * @description:
 */
@RestController
public class MovieController {
    @Autowired
    private UserFacadeClient userFacadeClient;

    @RequestMapping("/movie/{id}")
    public User findUserById(@PathVariable Long id){
        return userFacadeClient.findById(id);
    }
}
