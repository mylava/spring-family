package cn.mylava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * Feign电影微服务类
 * 1、启动 _003_discovery_eureka
 * 2、启动 _004_provider_user_eureka 服务
 * 3、启动 _015_consumer_movie_hystrix_fallbackfactory 服务
 * 4、访问 http://localhost:8081/movie/1
 * 5、Provider运行正常的情况下，返回正常User数据。
 * 6、停掉Provider服务，返回id=0的User数据，同时在控制台可以看到有异常日志输出
 * 7、启动Provider服务，又返回正常数据。
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
//EnableCircuitBreaker 表明需要集成断路器，断路器默认5秒内超过20次访问失败就会启动
@EnableCircuitBreaker
public class ConsumerMovieHystrixFallbackFactoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerMovieHystrixFallbackFactoryApplication.class, args);
	}
}
