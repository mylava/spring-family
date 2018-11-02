package cn.mylava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 简单电影微服务类
 * 1、启动 _003_discovery_eureka
 * 2、启动 两个服务名为 004-provider-user-eureka 的 _004_provider_user_eureka，端口分别为 8078，8079
 * 3、启动 _008_consumer_movie_ribbone_without_ureka 模块服务，访问N次 http://localhost:8081/movie/1
 * 5、发现服务没有通过Eureka做负载均衡，只有8078控制台有输出
 *
 */
@EnableEurekaClient
@SpringBootApplication
public class ConsumerMovieRibbonWithoutEurekaApplication {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

	public static void main(String[] args) {
		SpringApplication.run(ConsumerMovieRibbonWithoutEurekaApplication.class, args);
	}
}
