package cn.mylava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * Feign电影微服务类
 * 1、启动 _003_discovery_eureka
 * 2、启动 两个服务名为 004-provider-user-eureka 的 _004_provider_user_eureka，端口分别为 8078，8079
 * 3、启动 _013_consumer_movie_hystrix_propagation 模块服务
 * 4、正常访问 http://localhost:8081/movie/1 ，返回正常
 * 5、关闭provider，多次访问http://localhost:8081/movie/1，可以在 http://localhost:8081/health中看到断路器启动
 */
@SpringBootApplication
@EnableEurekaClient
//使用 @EnableFeignClients 就不需要 @EnableCircuitBreaker
@EnableFeignClients
//EnableCircuitBreaker 表明需要集成断路器
//@EnableCircuitBreaker
public class ConsumerMovieHystrixPropagationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerMovieHystrixPropagationApplication.class, args);
	}
}
