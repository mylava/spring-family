package cn.mylava.controller;

import cn.mylava.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
    private RestTemplate restTemplate;

    @Value("${user.userServicePath}")
    private String userServicePath;

    @RequestMapping("/movie/{id}")
    public User findUserById(@PathVariable Long id){
        return restTemplate.getForObject(userServicePath+id,User.class);
    }
}
