package cn.mylava.facade;

import cn.mylava.domain.User;
import config.FeignConfiguration1;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * comment:
 *
 * @author: lipengfei
 * @date: 29/10/2018
 * @company: (C) Copyright 58BTC 2018
 * @since: JDK 1.8
 * @description:
 */
@FeignClient(name="004-provider-user-eureka",configuration = FeignConfiguration1.class)
public interface UserFacade {

//    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    @RequestLine("GET /user/{id}")
    User findById(@Param("id") Long id);
}
