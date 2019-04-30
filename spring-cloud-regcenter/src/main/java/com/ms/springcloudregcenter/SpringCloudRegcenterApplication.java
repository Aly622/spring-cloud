package com.ms.springcloudregcenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpringCloudRegcenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudRegcenterApplication.class, args);
	}

}
