package cn.mylava.facade;

import cn.mylava.domain.User;
import cn.mylava.facade.fallback.UserFacadeFallbackFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * comment:
 * 总到来说，Feign的源码实现的过程如下：
 * 首先通过@EnableFeignCleints注解开启FeignCleint
 * 根据Feign的规则实现接口，并加@FeignCleint注解
 * 程序启动后，会进行包扫描，扫描所有的@ FeignCleint的注解的类，并将这些信息注入到ioc容器中。
 * 当接口的方法被调用，通过jdk的代理，来生成具体的RequesTemplate
 * RequesTemplate在生成Request
 * Request交给Client去处理，其中Client可以是HttpUrlConnection、HttpClient也可以是Okhttp
 * 最后Client被封装到LoadBalanceClient类，这个类结合类Ribbon做到了负载均衡。
 *
 * @author: lipengfei
 * @date: 29/10/2018
 * @company: (C) Copyright 58BTC 2018
 * @since: JDK 1.8
 * @description:
 */
@FeignClient(name="004-provider-user-eureka",fallbackFactory = UserFacadeFallbackFactory.class)
public interface UserFacadeClient {

    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    User findById(@PathVariable("id") Long id);
}
