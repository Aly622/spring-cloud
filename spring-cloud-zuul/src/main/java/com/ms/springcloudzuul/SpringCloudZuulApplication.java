package com.ms.springcloudzuul;

import com.ms.springcloudzuul.filter.PasswordFilter;
import com.ms.springcloudzuul.filter.TokenFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
//声明一个zuul代理，该代理使用ribbon来定位注册在eureka服务器中的微服务，同时该代理还整合了Hystrix，从而实现了容错
//当@EnableZuulProxy与springboot actuator配合使用时，zuul会暴露一个路由管理端点/routes
public class SpringCloudZuulApplication {
    //开启过滤器
    @Bean
    public TokenFilter tokenFilter() {
        return new TokenFilter();
    }
    @Bean
    public PasswordFilter passwordFilter() {
        return new PasswordFilter();
    }
	public static void main(String[] args) {
		SpringApplication.run(SpringCloudZuulApplication.class, args);
	}

}
