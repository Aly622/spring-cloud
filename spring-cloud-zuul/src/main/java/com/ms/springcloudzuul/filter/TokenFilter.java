package com.ms.springcloudzuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * @创建人 Oliver.Liu
 * @创建时间 3/21/2019
 * @描述 过滤器 token
 */
public class TokenFilter extends ZuulFilter {
    private static final Logger log = LoggerFactory.getLogger(TokenFilter.class);
    @Override
    public String filterType() {
        return "pre";//可以在请求被路由之前调用
    }
    /**
     filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下:
     pre：路由之前
     routing：路由之时
     post： 路由之后
     error：发送错误调用
     filterOrder：过滤的顺序
     shouldFilter：这里可以写逻辑判断，是否要过滤，本文true,永远过滤。
     run：过滤器的具体逻辑。可用很复杂，包括查sql，nosql去判断该请求到底有没有权限访问。
     */
    @Override
    public int filterOrder() {
        return 0;//filter执行顺序，通过数字指定，优先级为0，数字越大，优先级越低
    }

    @Override
    public boolean shouldFilter() {
        return true;//是否执行该过滤器，此处为true，表示需要过滤
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        log.info("----tokenfilter {}, {}", request.getMethod(), request.getRequestURL().toString());
        String token = request.getParameter("token");//获取请求的参数
        if(StringUtils.isNotBlank(token)) {
            ctx.setSendZuulResponse(true);//对请求进行路由
            ctx.setResponseStatusCode(200);
            ctx.set("isSuccess", true);
            return null;
        }
        else
        {
            ctx.setSendZuulResponse(false);//不对其进行路由
            ctx.setResponseStatusCode(400);
            ctx.setResponseBody("token is empty");
            ctx.set("isSuccess", false);
            return null;
        }
    }
}
