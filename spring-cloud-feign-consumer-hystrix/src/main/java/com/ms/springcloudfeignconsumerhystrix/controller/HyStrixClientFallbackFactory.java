package com.ms.springcloudfeignconsumerhystrix.controller;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @创建人 Oliver.Liu
 * @创建时间 4/2/2019
 * @描述
 */
@Component
public class HyStrixClientFallbackFactory implements FallbackFactory<HomeClient>{
    @Override
    public HomeClient create(Throwable throwable) {
        return () -> "feign + hystrix, 提供者服务挂了";
    }
}
