package cn.mylava.facade.fallback;

import cn.mylava.domain.User;
import cn.mylava.facade.TestFacade;
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
public class TestFacadeFallback implements TestFacade {
    @Override
    public String findServiceInfoFromEurekaByServiceName(String serviceName) {
        return "test服务出错！";
    }
}
