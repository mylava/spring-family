package cn.mylava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 服务发现服务端EurekaServer微服务
 *
 * 测试步骤
 * 1.启动 _003_discovery_eureka 服务
 * 2.打开 http://localhost:8761/eureka/apps 可以查看注册到该服务器上的一堆微服务实例的信息。
 */
@EnableEurekaServer
@SpringBootApplication
public class DiscoveryEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryEurekaApplication.class, args);
    }
}


