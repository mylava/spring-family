package cn.mylava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * Feign电影微服务类
 * 1、启动 _003_discovery_eureka
 * 2、启动 两个服务名为 004-provider-user-eureka 的 _004_provider_user_eureka，端口分别为 8078，8079
 * 3、启动 _014_consumer_movie_hystrix_particle 模块服务
 * 4、正常访问 http://localhost:8081/movie/1 ，http://localhost:8081/test/004-provider-user-eureka 返回正常
 * 5、关闭provider，访问http://localhost:8081/movie/1 ，hystrix有效（返回无意义的User，不报错）
 *    访问 http://localhost:8081/test/004-provider-user-eureka ，hystrix无效（报错）
 *
 * 原理：
 * 源码 FeignClientsConfiguration 类中，HystrixFeignConfiguration 内部类定义了默认的 feignHystrixBuilder 方法，
 * 该方法返回 HystrixFeign.builder()
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class ConsumerMovieHystrixParticleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerMovieHystrixParticleApplication.class, args);
	}
}
