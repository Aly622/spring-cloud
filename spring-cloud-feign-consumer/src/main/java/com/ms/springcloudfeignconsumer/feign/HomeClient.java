package com.ms.springcloudfeignconsumer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @创建人 Oliver.Liu
 * @创建时间 4/1/2019
 * @描述
 */
@FeignClient("provider000")
public interface HomeClient {
    @GetMapping("/")
    String consumer();
}
