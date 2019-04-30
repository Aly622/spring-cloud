package com.ms.springcloudribbonconsumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @创建人 Oliver.Liu
 * @创建时间 4/1/2019
 * @描述
 */
@RestController
public class ConsumerController {
    @Autowired
    private RestTemplate restTemplate;
    @GetMapping(value = "/hello")
    public String hello(){
        return restTemplate.getForEntity("http://provider000/", String.class).getBody();
    }
}
