package com.ms.springcloudribbonconsumerhystrix.controller;

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
    /* *
    fallbackMethod 降级方法
    commandProperties 普通配置属性，可以配置HystrixCommand对应属性，例如采用线程池还是信号量隔离、熔断器熔断规则等等
    ignoreExceptions 忽略的异常，默认HystrixBadRequestException不计入失败
    groupKey() 组名称，默认使用类名称
    commandKey 命令名称，默认使用方法名
    * */
    @HystrixCommand(fallbackMethod = "defaultStores")
    @GetMapping(value = "/hello")
    public String hello() {
        return restTemplate.getForEntity("http://provider/", String.class).getBody();
    }
    public String defaultStores() {
        return "Ribbon + hystrix, 提供者服务挂了";
    }
}
