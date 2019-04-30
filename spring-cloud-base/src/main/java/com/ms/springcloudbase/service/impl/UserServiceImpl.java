package com.ms.springcloudbase.service.impl;

import com.ms.springcloudbase.bean.User;
import com.ms.springcloudbase.dao.UserMapper;
import com.ms.springcloudbase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

/**
 * @创建人 Oliver.Liu
 * @创建时间 4/10/2019
 * @描述
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    /*
      可以标记在一个方法上，也可以标记在一个类上。当标记在一个方法上时表示该方法是支持缓存的，
      当标记在一个类上时则表示该类所有的方法都是支持缓存的。对于一个支持缓存的方法，Spring会在其被调用后将其返回值缓存起来，
      以保证下次利用同样的参数来执行该方法时可以直接从缓存中获取结果，而不需要再次执行该方法。另外这里运用到了springBoot的自动配置原理。
      springBoot默认是以ConcurrentHashMap作为底层存储数据结构，当引入redis启动器后，它的自动配置会扫描到redis的存在，
      从将CacheManager实现类改为使用redis实现。这就是springBoot“开箱即用”的关键所在，根据当前项目环境，做出最合适的配置。
    */
    @Cacheable(value = "user", key = "'user'")
    @Override
    public User selectByPrimaryKey(Integer id) {
        User user = null;
        System.out.println("开始查询.....");
        try {
            Thread.sleep(3 * 1000);
            user = userMapper.selectByPrimaryKey(Long.valueOf(id));
            Thread.sleep(3 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("查询结束......");
        return user;
    }
}
