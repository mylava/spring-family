package cn.mylava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Eureka电影微服务类
 * 1、启动 _003_discovery_eureka
 * 2、启动 _004_provider_user_eureka 模块服务
 * 3、启动 _005_consumer_movie_eureka 模块服务
 * 4、在浏览器输入地址 http://localhost:8081/movie/1 可以看到信息成功的被打印出来；
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
