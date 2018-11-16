package cn.mylava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 服务发现服务端EurekaServer微服务
 *
 * 测试步骤
 * 1.分别使用不同的profile（run配置的Active Profile参数），启动多个 _011_discovery_eureka_ha 服务
 * 2.打开 http://localhost:8761/ 可以查看注册多个eureka服务。
 */
@EnableEurekaServer
@SpringBootApplication
public class DiscoveryEurekaHaApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryEurekaHaApplication.class, args);
    }
}


