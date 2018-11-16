package cn.mylava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;

/**
 * Gateway 网关类
 *
 * 测试步骤
 * 1.启动 _003_discovery_eureka 服务
 * 2.打开 http://localhost:8761/eureka/apps 可以查看注册到该服务器上的一堆微服务实例的信息。
 * 注意 EnableZuulProxy 注解能注册到 eureka 服务上，是因为该注解包含了 eureka 客户端的注解，该 EnableZuulProxy 是一个复合注解。
 *
 * @EnableZuulProxy --> { @EnableCircuitBreaker、@EnableDiscoveryClient } 包含了 eureka 客户端注解，同时也包含了 Hystrix 断路器模块注解。
 *
 * http://localhost:8150/routes 地址可以查看该zuul微服务网关代理了多少微服务的serviceId。
 *
 *
 */
@EnableZuulProxy
@SpringBootApplication
public class GatewayZuulFallbackApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayZuulFallbackApplication.class, args);
    }
}


