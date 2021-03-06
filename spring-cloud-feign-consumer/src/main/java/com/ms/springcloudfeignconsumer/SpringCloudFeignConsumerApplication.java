package com.ms.springcloudfeignconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudFeignConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudFeignConsumerApplication.class, args);
	}

}
