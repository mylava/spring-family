package cn.mylava.controller;

import cn.mylava.facade.UserFacadeClient;
import cn.mylava.domain.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * comment:
 *
 * @author: lipengfei
 * @date: 10/10/2018
 * @company: (C) Copyright 58BTC 2018
 * @since: JDK 1.8
 * @description:
 */
@RestController
public class MovieController {
    @Autowired
    private UserFacadeClient userFacadeClient;

    /**
     * Hystrix 通过舱壁模式来隔离限制依赖的并发量和阻塞扩散
     * Hystrix 提供了两种隔离策略：线程池隔离(THREAD，默认策略)和信号量隔离(SEMAPHORE)。
     * ---------------------
     * 线程池隔离：
     * 线程池隔离把执行依赖代码的线程与请求线程(如：tomcat 线程)分离，请求线程可以自由控制 离开的时间。
     * 通过线程池大小可以控制并发量，当线程池饱和时可以提前拒绝服务，防止依赖问题扩散。
     * 生产环境建议线程池（默认是 10 个线程）不要设置过大，否则大量堵塞线程有可能会拖慢服务器。
     *
     * 优点：
     * 1、 使用线程池隔离可以完全隔离第三方应用，请求线程可以快速放回。
     * 2、 请求线程可以继续接受新的请求，如果出现问题线程池隔离是独立的不会影响其他应用。
     * 3、 当失败的应用再次变得可用时，线程池将清理并可立即恢复，而不需要一个长时间的恢复。
     * 4、 独立的线程池提高了并发性。
     * 注意：尽管线程池隔离是由一个单独的线程提供，客户端代码（异常方法里面的请求）应该也 有超时机制，不能让响应的线程无限期等待，应该适时去中断它，阻止 Hystrix 线程池的饱和。
     * 缺点：
     * 线程池隔离的主要缺点是它们增加计算开销（CPU）。每个命令的执行涉及到排队、调度和上 下文切换都是在一个单独的线程上运行的。
     * ---------------------
     * 信号量隔离：
     * 使用一个原子计数器（或信号量）来记录当前有多少个线程在运行，当请求进来时先判断计数器的数值，若超过设置的最大线程个数则拒绝该请求，若不超过则通行，这时候计数器+1，请求返 回成功后计数器-1。
     * 与线程池隔离最大不同在于执行依赖代码的线程依然是请求线程
     * tips：信号量的大小可以动态调整, 线程池大小不可以
     * ---------------------
     * 应用场景：
     * 线程池隔离：
     * 1、 第三方应用或者接口
     * 2、 并发量大
     * 信号量隔离：
     * 1、 内部应用或者中间件（redis）
     * 2、 并发需求不大
     *
     * @param id
     * @return
     */
//    使用feign的fallback（facade包）
//    @HystrixCommand(fallbackMethod = "findUserByIdFallback", commandProperties=@HystrixProperty(name= HystrixPropertiesManager.EXECUTION_ISOLATION_STRATEGY,value = "SEMAPHORE"))
    @RequestMapping("/movie/{id}")
    public User findUserById(@PathVariable Long id){
        return userFacadeClient.findById(id);
    }

    /**
     * 当 springms-provider-user 请求超时(服务宕机或者不可用)后会调用此方法。
     * fallback方法要求： 签名与被标注的方法一致
     * @param id
     * @return User
     */
    public User findUserByIdFallback(@PathVariable Long id){
        User user = new User();
        user.setId(0l);
        return user;
    }
}
