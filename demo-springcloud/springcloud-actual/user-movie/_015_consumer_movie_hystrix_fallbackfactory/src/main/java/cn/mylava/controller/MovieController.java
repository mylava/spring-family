package cn.mylava.controller;

import cn.mylava.facade.UserFacadeClient;
import cn.mylava.domain.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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

    /**
     * 当 springms-provider-user 请求超时(服务宕机或者不可用)后会调用此方法。
     * fallback方法要求： 签名与被标注的方法一致
     * @param id
     * @return User
     */
    public User findUserByIdFallback(@PathVariable Long id){
        User user = new User();
        user.setId(0l);
        return user;
    }
}
