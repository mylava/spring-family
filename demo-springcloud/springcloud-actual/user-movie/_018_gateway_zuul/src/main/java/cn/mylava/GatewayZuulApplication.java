package cn.mylava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Gateway 网关类
 *
 * 测试步骤
 * 1.启动 _003_discovery_eureka 服务
 * 2.启动 _004-provider-user-eureka、_012_consumer_movie_hystrix
 * 3.打开 http://localhost:8040/routes 地址可以查看该zuul微服务网关代理了多少微服务的serviceId。
 * 4.访问 http://localhost:8040/{prefix}/{serviceId}/user/1 ，可以看到通过网关访问user微服务的效果
 * 5.同样，访问 http://localhost:8040/{prefix}/{serviceId}/movie/2 ，可以看到通过网关访问movie微服务的效果。
 *
 * 注意 EnableZuulProxy 注解能注册到 eureka 服务上，是因为该注解包含了 eureka 客户端的注解，
 * 该 EnableZuulProxy 是一个复合注解。
 *
 * @EnableZuulProxy --> { @EnableCircuitBreaker、@EnableDiscoveryClient } 包含了 eureka 客户端注解，
 * 同时也包含了 Hystrix 断路器模块注解。
 *
 *
 *
 *
 */
@EnableZuulProxy
@SpringBootApplication
public class GatewayZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayZuulApplication.class, args);
    }
}


