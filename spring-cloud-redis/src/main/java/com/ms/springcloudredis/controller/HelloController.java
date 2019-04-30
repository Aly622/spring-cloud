package com.ms.springcloudredis.controller;

import com.ms.springcloudbase.bean.User;
import com.ms.springcloudbase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @创建人 Oliver.Liu
 * @创建时间 4/11/2019
 * @描述
 */
@RestController
public class HelloController {
    @Autowired
    private UserService userService;

    @RequestMapping("/hello")
    public String index() {
        long beginTime=System.currentTimeMillis();
        User user = userService.selectByPrimaryKey(49);
        long time=System.currentTimeMillis()-beginTime;
        return "Hello SpringBoot"+user.getName()+",消耗查询时间："+time;
    }
}
