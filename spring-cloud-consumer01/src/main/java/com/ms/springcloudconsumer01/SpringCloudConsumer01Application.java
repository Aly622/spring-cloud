package com.ms.springcloudconsumer01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudConsumer01Application {
	public static void main(String[] args) {
		SpringApplication.run(SpringCloudConsumer01Application.class, args);
	}
}
