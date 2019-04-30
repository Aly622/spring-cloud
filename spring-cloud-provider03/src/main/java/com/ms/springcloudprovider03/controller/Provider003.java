package com.ms.springcloudprovider03.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @创建人 Oliver.Liu
 * @创建时间 4/1/2019
 * @描述
 */
@RefreshScope
@RestController
public class Provider003 {
    @Value("${server.port}")
    String port;
    @Value("${content}")
    String content;
    @RequestMapping("/")
    public String home(){
        return "Hello world! port:" + port + ",content:" + content;
    }
}
