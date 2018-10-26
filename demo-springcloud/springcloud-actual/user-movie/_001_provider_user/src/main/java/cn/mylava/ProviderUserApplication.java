package cn.mylava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 简单用户微服务：
 *
 * 1、启动 _001_provider_user 模块服务
 * 2、在浏览器输入地址 http://localhost:8000/user/1 可以看到信息成功的被打印出来。
 */
@SpringBootApplication
public class ProviderUserApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProviderUserApplication.class, args);
	}
}
