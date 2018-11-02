package cn.mylava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * Feign电影微服务类
 * 1、启动 _003_discovery_eureka
 * 2、启动 两个服务名为 004-provider-user-eureka 的 _004_provider_user_eureka，端口分别为 8078，8079
 * 3、启动 _009_consumer_movie_feign 模块服务，
 * 5、访问 http://localhost:8081/movie/1 ，通过feign接口调用成功
 *
 * 虽然说我们可以采用 RestTemplate、URLConnection、Netty、HttpClient都可以访问远端 HTTP 服务器，
 * 但是使用 Feign 来说，Feign 可以做到使用 HTTP 请求远程服务时就像调用本地的方法一样，让开发者完全感知不到这是在调用远端服务，感觉无非就是调用一个 API 方法一样；
 * 当我们使用 Feign 的时候，SpringCloud 整合了 Ribbon 和 Eureka 去提供负载均衡；
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class ConsumerMovieFeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerMovieFeignApplication.class, args);
	}
}
