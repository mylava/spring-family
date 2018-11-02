package cn.mylava.controller;

import cn.mylava.facade.TestFacade;
import cn.mylava.facade.UserFacade;
import cn.mylava.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    private UserFacade userFacadeClient;

    @Autowired
    private TestFacade testFacade;

    @RequestMapping("/movie/{id}")
    public User findUserById(@PathVariable Long id){
        return userFacadeClient.findById(id);
    }

    @GetMapping("/{serviceName}")
    public String findServiceInfoFromEurekaByServiceName(@PathVariable String serviceName) {
        return this.testFacade.findServiceInfoFromEurekaByServiceName(serviceName);
    }
}
