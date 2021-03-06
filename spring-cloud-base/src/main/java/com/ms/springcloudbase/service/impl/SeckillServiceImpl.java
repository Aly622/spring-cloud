package com.ms.springcloudbase.service.impl;

import com.ms.springcloudbase.bean.Seckill;
import com.ms.springcloudbase.bean.SuccessKilled;
import com.ms.springcloudbase.dao.SeckillMapper;
import com.ms.springcloudbase.dao.SuccessKilledMapper;
import com.ms.springcloudbase.pojo.QueueEntity;
import com.ms.springcloudbase.pojo.SecKillResult;
import com.ms.springcloudbase.pool.ExecutorPool;
import com.ms.springcloudbase.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @创建人 Oliver.Liu
 * @创建时间 4/15/2019
 * @描述
 */
@Service
public class SeckillServiceImpl implements SeckillService {
    @Autowired
    private SeckillMapper seckillMapper;
    @Autowired
    private SuccessKilledMapper successKilledMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public List<Seckill> getAllSecKill() {
        return seckillMapper.selectAll();
    }

    @Override
    public SecKillResult secKillProduct(String userPhone, long productId) {
        String state = (String)redisTemplate.opsForValue().get(userPhone + "_"+ productId);
        //用户信息加载
        if(null == state){
            SuccessKilled successKilled = new SuccessKilled();
            successKilled.setSeckillId(productId);
            successKilled.setUserPhone(Long.valueOf(userPhone));
            successKilled = successKilledMapper.selectByPrimaryKey(successKilled);
            if(null == successKilled){
                return new SecKillResult(false, "该用户没有预约");
            }else{
                synchronized (this){
                    state = (String)redisTemplate.opsForValue().get(userPhone + "_"+ productId);
                    if(null == state){
                        redisTemplate.opsForValue().set(userPhone + "_" + productId, successKilled.getState().toString(), 300, TimeUnit.SECONDS);
                        state = String.valueOf(successKilled.getState());
                    }
                }
            }
        }
        if(state.equals("-1")){
            //查询产品信息
//            ProductInfo productInfo = (ProductInfo)redisTemplate.opsForValue().get(productId + "");
            List values = redisTemplate.opsForHash().values(productId + "");
            if(values.size() == 0){
                Seckill seckill = seckillMapper.selectByPrimaryKey(productId);
                if(null == seckill){
                    return new SecKillResult(false, "没有该秒杀商品信息");
                }
                synchronized (this){
                    if(!redisTemplate.opsForHash().hasKey(productId + "", "number")){
//                        productInfo = new ProductInfo(seckill.getSeckillId(), seckill.getNumber(), seckill.getStartTime(), seckill.getEndTime());
                        HashMap<String, String> productHash = new HashMap<>();
                        productHash.put("number", seckill.getNumber() + "");
                        productHash.put("startTime", seckill.getStartTime().getTime() + "");
                        productHash.put("endTime", seckill.getEndTime().getTime() + "");
                        redisTemplate.opsForHash().putAll(productId +"", productHash);
                        redisTemplate.expire(productId + "", 300, TimeUnit.SECONDS);
                        values = redisTemplate.opsForHash().values(productId + "");
                    }
                }
            }
            if( new Date(Long.valueOf((String)values.get(1))).after(new Date(System.currentTimeMillis()))){
                return new SecKillResult(false, "抢购还没有开始");
            } else if(new Date(Long.valueOf((String)values.get(2))).before(new Date(System.currentTimeMillis()))){
                return new SecKillResult(false, "抢购已经结束");
            } else {
                Long userState = redisTemplate.opsForValue().increment(userPhone + "_" + productId, 1);
                if(userState == 0){
//                    Long increment = redisTemplate.opsForValue().increment(productId, -1);
                    Long number = redisTemplate.opsForHash().increment(productId + "", "number", -1);
                    if(number >= 0){
                        //消息队列异步更新库存，以及用户的预约信息
                        QueueEntity queueEntity = new QueueEntity(userPhone, productId);
                        ExecutorPool.queue.offer(queueEntity);
                    }else {
                        return new SecKillResult(false, "商品已经抢购完成");
                    }
                }else {
                    redisTemplate.opsForValue().increment(userPhone + "_" + productId, -1);
                    return new SecKillResult(false, "您已抢购过该产品");
                }
            }
        } else {
            return new SecKillResult(false, "您已抢购过该产品");
        }
        return null;
    }
}
