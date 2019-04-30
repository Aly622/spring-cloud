package com.ms.springcloudzuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * @创建人 Oliver.Liu
 * @创建时间 3/21/2019
 * @描述 过滤器
 */
public class PasswordFilter extends ZuulFilter {
    private static final Logger log = LoggerFactory.getLogger(PasswordFilter.class);
    @Override
    public String filterType() {
        return "post";// 请求处理完成后执行的filter
    }

    @Override
    public int filterOrder() {
        return 1; //优先级为0，数字越大，优先级越低
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        return (boolean)ctx.get("isSuccess");
        // return false; 判断上一个过滤器结果为true，否则就不走下面的过滤器，直接跳过后面的所有过滤器并返回上一个过滤器不通过的结果
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info("-----PasswordFilter {}, {}.", request.getMethod(), request.getRequestURL().toString());
        String username = request.getParameter("password");
        if(null != username && username.equals("123456")){
            ctx.setSendZuulResponse(true);
            ctx.setResponseStatusCode(200);
            ctx.set("isSuccess", true);
            return null;
        }
        else
        {
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(400);
            ctx.setResponseBody("The password cannot be empty");
            ctx.set("isSuccess", false);
            return null;
        }
    }
}
