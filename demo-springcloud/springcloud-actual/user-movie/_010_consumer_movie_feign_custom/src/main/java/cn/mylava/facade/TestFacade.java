package cn.mylava.facade;

import cn.mylava.domain.User;
import config.TestFacadeConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * comment:
 *
 * @author: lipengfei
 * @date: 01/11/2018
 * @company: (C) Copyright 58BTC 2018
 * @since: JDK 1.8
 * @description:
 */
@FeignClient(name = "xxxx", url = "http://localhost:8761/", configuration = TestFacadeConfiguration.class)
public interface TestFacade {

    @RequestMapping(value = "/eureka/apps/{serviceName}")
    String findServiceInfoFromEurekaByServiceName(@PathVariable("serviceName") String serviceName);

    /**
     * 如果入参是一个对象的话，那么这个方法在 feign 里面默认为 POST 方法，就算你写成 GET 方式也无济于事。
     * @param user
     * @return
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public User postUser(@RequestBody User user);
}
