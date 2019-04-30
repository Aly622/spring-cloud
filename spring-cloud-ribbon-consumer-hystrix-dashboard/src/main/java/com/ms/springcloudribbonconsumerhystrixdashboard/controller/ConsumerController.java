package com.ms.springcloudribbonconsumerhystrixdashboard.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @创建人 Oliver.Liu
 * @创建时间 4/2/2019
 * @描述
 */
@RestController
public class ConsumerController {
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "defaultStores")
    @GetMapping(value = "/hello")
    public String hello() {
        return restTemplate.getForEntity("http://provider/", String.class).getBody();
    }

    public String defaultStores() {
        return "feign + hystrix Dashboard, 提供者服务挂了";
    }
}
