package config;

import feign.Contract;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * comment:
 *
 * @author: lipengfei
 * @date: 31/10/2018
 * @company: (C) Copyright 58BTC 2018
 * @since: JDK 1.8
 * @description:
 */
@Configuration
public class FeignConfiguration1 {

    @Bean
    public Contract feignContract() {
        return new Contract.Default();
    }

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
