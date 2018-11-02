package cn.mylava.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * comment: 默认采用轮询策略，这里修改为随机策略。
 *
 * 1.这个测试配置类不能放在@ComponentScan所扫描的当前包下以及子包下，
 * 否则我们自定义的这个配置类就会被所有的Ribbon客户端所共享（比如还依赖另外一个购物车provider，
 * 即使@RibonClient中的name指定是user-provider，购物车也会使用这个策略）
 *
 * 2.如果想要将这个类放在@Component扫描的包或子包下，需要添加一个注解，表明Component扫描时排除此类
 *
 * @author: lipengfei
 * @date: 29/10/2018
 * @company: (C) Copyright 58BTC 2018
 * @since: JDK 1.8
 * @description:
 */
@Configuration
@ExcludeFromComponentScan
public class RibbonConfiguration {
/*    //如果不放在扫描包下，需要引入此属性，且方法中需要有 IClientConfig config 参数
    @Autowired
    IClientConfig config;

    @Bean
    public IRule ribbonRule(IClientConfig config) {
        return new RandomRule();
    }
    */

    @Bean
    public IRule ribbonRule() {
        return new RandomRule();
    }
}
