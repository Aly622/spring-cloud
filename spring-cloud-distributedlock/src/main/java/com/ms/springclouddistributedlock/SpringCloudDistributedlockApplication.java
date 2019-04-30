package com.ms.springclouddistributedlock;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudDistributedlockApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudDistributedlockApplication.class, args);
    }
    @Bean
    Redisson redissonSentinel() {
        //支持单机，主机，哨兵，集群等模式
        //因为只有一台服务器 所以使用单机模式
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://192.168.20.212:6379")
                .setPassword("Liujc123");
        return (Redisson)Redisson.create(config);
    }
}
