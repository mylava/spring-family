package cn.mylava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 简单电影微服务类
 * 1、启动 _001_provider_user 模块服务
 * 2、启动 _002_consumer_movie 模块服务
 * 3、在浏览器输入地址 http://localhost:8081/movie/1 可以看到信息成功的被打印出来；
 */
@EnableEurekaClient
@SpringBootApplication
public class ConsumerMovieEurekaApplication {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(ConsumerMovieEurekaApplication.class, args);
	}
}
