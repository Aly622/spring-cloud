package com.ms.springcloudbase.service;

import com.ms.springcloudbase.bean.Seckill;
import com.ms.springcloudbase.pojo.SecKillResult;

import java.util.List;

/**
 * @创建人 Oliver.Liu
 * @创建时间 4/15/2019
 * @描述
 */
public interface SeckillService {
    List<Seckill> getAllSecKill();

    SecKillResult secKillProduct(String userPhone, long productId);
}
