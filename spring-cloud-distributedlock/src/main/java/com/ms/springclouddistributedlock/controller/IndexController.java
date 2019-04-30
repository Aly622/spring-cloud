package com.ms.springclouddistributedlock.controller;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
@Slf4j
@RestController
public class IndexController {
    //商品key
    private static String commodityCount = "commodityCount";
    // 分布式锁的key
    private static String lockKey = "testRedisson";
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private Redisson redisson;
    private int index = 0;
    // 查询是否健康
    @RequestMapping(value = "/health", method = RequestMethod.GET)
    public String health() {
        return "health";
    }
    //设置商品数量为100个
    @RequestMapping("/setValue")
    public String setValue(int value) {
        redisTemplate.opsForValue().set(commodityCount, value + "");
        return "success";
    }
    // 模拟秒杀抢购，并发200个请求过来，查看是否出现超卖
    @RequestMapping("/spike")
    public String spike() {
        String flag = "success";
        RLock lock = redisson.getLock(lockKey);
        try {
            //lock.lockAsync(5, TimeUnit.SECONDS);
            //lock.lock(5, TimeUnit.SECONDS); 设置60秒自动释放锁 默认是30秒自动过期
            Future<Boolean> res = lock.tryLockAsync(100, 5, TimeUnit.SECONDS);
            boolean result = res.get();
            System.out.println("seckill result:" + result);
            log.info("seckill result:" + result);
            if(result) {
                index ++;
                System.out.println("seckill index:" + index);
                log.info("seckill index:" + index);
                int stock = Integer.parseInt(redisTemplate.opsForValue().get(commodityCount).toString());
                log.info("seckill stock:" + stock);
                System.out.println("seckill stock:" + stock);
                if(stock > 0) {
                    redisTemplate.opsForValue().set(commodityCount, (stock - 1)+"");
                } else {
                    flag = "fail";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();//释放锁
        }
        return flag;
    }
}
