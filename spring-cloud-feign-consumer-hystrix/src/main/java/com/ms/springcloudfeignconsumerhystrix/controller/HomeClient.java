package com.ms.springcloudfeignconsumerhystrix.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @创建人 Oliver.Liu
 * @创建时间 4/2/2019
 * @描述
 */
@FeignClient(value = "provider", fallbackFactory = HyStrixClientFallbackFactory.class)
public interface HomeClient {
    @GetMapping("/")
    String consumer();
}
