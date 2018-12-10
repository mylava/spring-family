package cn.mylava.scope;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

/**
 * comment:
 *
 * @author: lipengfei
 * @date: 16/08/2018
 */
@PropertySource(value = "classpath:datasource.properties",ignoreResourceNotFound = true)
@Configuration //标明此类为spring的配置类
@ComponentScan("cn.mylava.scope.bean")
public class MyConf {

    @Value("${jdbc.driverclass}")
    private String driverClassName;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;


    @Bean(destroyMethod = "close")
    public DruidDataSource druidDataSource() {
        DruidDataSource dataSource = new DruidDataSource();

        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }
}
