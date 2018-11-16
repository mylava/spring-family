package cn.mylava.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

/**
 * comment:
 *
 * @author: lipengfei
 * @date: 15/11/2018
 * @company: (C) Copyright 58BTC 2018
 * @since: JDK 1.8
 * @description:
 */
@Slf4j
public class PreZuulFilter extends ZuulFilter {
    /**
     * filter的类型，有pre route post error四种类型
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * filter的执行顺序
     * 0 表示最前执行
     * @return
     */
    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * 是否使用过此滤器
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * filter要执行的业务逻辑
     * @return
     */
    @Override
    public Object run() {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();

        String remoteHost = request.getRemoteHost();
        log.info("请求的host是 {}, port是 {}, address 是 {}",remoteHost, request.getRemotePort(), request.getRemoteAddr());
        return null;
    }
}
