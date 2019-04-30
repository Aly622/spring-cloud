package com.ms.springcloudredis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.ms.springcloudbase.dao")
@ComponentScan({"com.ms.springcloudbase.service","com.ms.springcloudredis.controller"})
@SpringBootApplication
//@Import()
public class SpringCloudRedisApplication {
	public static void main(String[] args) {
	    SpringApplication.run(SpringCloudRedisApplication.class, args);
	}
}
