package cn.mylava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * eureka用户微服务：
 *
 * 1.启动 _003_discovery_eureka 服务
 * 2.启动 _004_provider_user_eureka 服务
 * 3.打开 http://localhost:8761，输入用户名密码 admin/admin
 * 4.在eureka首页中可以看到，_004_provider_user_eureka 注册到了instances 中
 */
@EnableEurekaClient
@SpringBootApplication
public class ProviderUserEurekaApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProviderUserEurekaApplication.class, args);
	}
}
