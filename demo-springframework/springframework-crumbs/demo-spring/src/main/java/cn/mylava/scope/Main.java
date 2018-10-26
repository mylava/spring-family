package cn.mylava.scope;

import cn.mylava.scope.bean.ScopeTest;
import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * comment: 演示@Scope的用法
 *
 * @author: lipengfei
 * @date: 16/08/2018
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(MyConf.class);
        ScopeTest bean1 = ctx.getBean(ScopeTest.class);
        ScopeTest bean2 = ctx.getBean(ScopeTest.class);
        System.out.println(bean1.equals(bean2));

        DruidDataSource bean = ctx.getBean(DruidDataSource.class);
        System.out.println(bean);
        ctx.close();
    }
}
