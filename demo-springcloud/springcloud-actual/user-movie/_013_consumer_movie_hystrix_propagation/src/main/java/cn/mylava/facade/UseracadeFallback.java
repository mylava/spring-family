package cn.mylava.facade;

import cn.mylava.domain.User;
import org.springframework.stereotype.Component;

/**
 * comment:
 *
 * @author: lipengfei
 * @date: 05/11/2018
 * @company: (C) Copyright 58BTC 2018
 * @since: JDK 1.8
 * @description:
 */
@Component
public class UseracadeFallback implements UserFacadeClient{
    @Override
    public User findById(Long id) {
        User user = new User();
        user.setId(0l);
        return user;
    }
}
