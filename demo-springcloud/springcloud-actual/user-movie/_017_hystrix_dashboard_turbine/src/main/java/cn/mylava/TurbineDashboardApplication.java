package cn.mylava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * hystrix turbine
 * 1、启动 _003_discovery_eureka
 * 2、启动 _004-provider-user-eureka、_015_consumer_movie_hystrix_fallbackfactory
 * 3、启动 _016_hystrix_dashboard 模块服务，
 * 4、访问 http://localhost:8081/movie/1
 * 5、访问 http://localhost:8030/hystrix
 * 6、在hystrix dashboard页面 输入 http://localhost:8081/hystrix.stream 和 title信息
 * 7、可以看到dashboard的图形化展示。
 */
@EnableTurbine
@SpringBootApplication
public class TurbineDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(TurbineDashboardApplication.class, args);
	}
}
