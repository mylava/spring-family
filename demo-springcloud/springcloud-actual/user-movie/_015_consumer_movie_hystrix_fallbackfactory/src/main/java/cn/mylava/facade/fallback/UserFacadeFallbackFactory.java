package cn.mylava.facade.fallback;

import cn.mylava.domain.User;
import cn.mylava.facade.UserFacadeClient;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Component
public class UserFacadeFallbackFactory implements FallbackFactory<UserFacadeClient>{

    /**
     * fallbackFactory 与 fallback 区别：
     * fallbackFactory 实现 FallbackFactory 接口，这个接口定义create方法包含异常信息，
     * 在想要获取fallback原因是，可以使用FallbackFabtory
     * 
     * @param throwable
     * @return
     */
    @Override
    public UserFacadeClient create(Throwable throwable) {
        log.info("fallback; reason was {}",throwable.getMessage());
        return new UserFacadeClient() {
            @Override
            public User findById(Long id) {
                User user = new User();
                user.setId(-1l);
                return user;
            }
        };
    }
}
