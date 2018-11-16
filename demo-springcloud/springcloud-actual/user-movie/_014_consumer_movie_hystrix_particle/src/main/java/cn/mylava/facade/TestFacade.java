package cn.mylava.facade;

import cn.mylava.domain.User;
import cn.mylava.facade.fallback.TestFacadeFallback;
import config.TestFacadeConfiguration;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * comment:
 *
 * @author: lipengfei
 * @date: 01/11/2018
 * @company: (C) Copyright 58BTC 2018
 * @since: JDK 1.8
 * @description:
 */
@FeignClient(name = "xxxx", url = "http://localhost:8761/", configuration = TestFacadeConfiguration.class,fallback = TestFacadeFallback.class)
public interface TestFacade {

    @RequestMapping(value = "/eureka/apps/{serviceName}")
    String findServiceInfoFromEurekaByServiceName(@PathVariable("serviceName") String serviceName);
}
