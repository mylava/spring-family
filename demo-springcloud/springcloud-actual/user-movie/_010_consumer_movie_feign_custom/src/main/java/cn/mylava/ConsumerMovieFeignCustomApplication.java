package cn.mylava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * Feign电影微服务类
 * 1、启动 _003_discovery_eureka
 * 2、启动 两个服务名为 004-provider-user-eureka 的 _004_provider_user_eureka，端口分别为 8078，8079
 * 3、启动 _010_consumer_movie_feign_custom 模块服务，
 * 5、访问 http://localhost:8081/movie/1 ，通过feign接口调用成功
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class ConsumerMovieFeignCustomApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerMovieFeignCustomApplication.class, args);
	}
}
