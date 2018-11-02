package config;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * comment:
 *
 * @author: lipengfei
 * @date: 01/11/2018
 * @company: (C) Copyright 58BTC 2018
 * @since: JDK 1.8
 * @description:
 */
@Configuration
public class Configuration2 {
    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("admin", "admin");
    }
}
