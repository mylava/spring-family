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
 * 3、启动 _007_consumer_movie_eureka_ribbon_yml 模块服务，访问N次 http://localhost:8081/movie/1
 * 5、8078，8079两个控制台看到请求次数是随机的
 *
 * https://blog.csdn.net/ylimh_hmily/article/details/78011862
 */
@EnableEurekaClient
@SpringBootApplication
public class ConsumerMovieEurekaRibbonYmlApplication {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

	public static void main(String[] args) {
		SpringApplication.run(ConsumerMovieEurekaRibbonYmlApplication.class, args);
	}
}
