package cn.mylava.controller;

import cn.mylava.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * comment:
 *
 * @author: lipengfei
 * @date: 10/10/2018
 * @company: (C) Copyright 58BTC 2018
 * @since: JDK 1.8
 * @description:
 */
@Slf4j
@RestController
public class MovieController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @RequestMapping("/movie/{id}")
    public User findUserById(@PathVariable Long id){
        return restTemplate.getForObject("http://004-provider-user-eureka/user/"+id,User.class);
    }

    //测试 ribbon 配置类放在ComponentScan包下，且没有ExcludeFromComponentScan注解时，ribbon策略会共享的情况
    //步骤：
    //1、启动 _003_discovery_eureka
    //2、启动 两个服务名为 004-provider-user-eureka 的 _004_provider_user_eureka，端口分别为 8078，8079
    //3、再启动两个服务名为 004-provider-user-eureka2 的 _004_provider_user_eureka，端口分别为8178，8179
    //4、启动 _006_consumer_movie_eureka 模块服务，访问N次 http://localhost:8081/test
    //5、发现虽然RibbonClient指定了name = 004-provider-user-eureka，但是004-provider-user-eureka2的负载均衡策略也是随机的
    @GetMapping("/test")
    public String test() {
        ServiceInstance serviceInstance = loadBalancerClient.choose("004-provider-user-eureka");
        log.info(serviceInstance.getHost()+":"+serviceInstance.getPort()+":"+serviceInstance.getServiceId());

        ServiceInstance serviceInstance2 = loadBalancerClient.choose("004-provider-user-eureka2");
        log.info(serviceInstance2.getHost()+":"+serviceInstance2.getPort()+":"+serviceInstance2.getServiceId());
        return "test";
    }


}
