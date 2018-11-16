package cn.mylava.facade.fallback;

import cn.mylava.domain.User;
import cn.mylava.facade.UserFacade;
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
public class UserFacadeFallback implements UserFacade{
    @Override
    public User findById(Long id) {
        User user = new User();
        user.setId(1l);
        return user;
    }
}
