package com.ms.springcloudbase.service.impl;

import com.ms.springcloudbase.bean.SuccessKilled;
import com.ms.springcloudbase.dao.SeckillMapper;
import com.ms.springcloudbase.dao.SuccessKilledMapper;
import com.ms.springcloudbase.service.SuccesskilledService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @创建人 Oliver.Liu
 * @创建时间 4/15/2019
 * @描述
 */
@Service
public class SuccesskilledServiceImpl implements SuccesskilledService {
    @Autowired
    private SeckillMapper seckillMapper;
    @Autowired
    SuccessKilledMapper successKilledMapper;
    @Override
    public void saveSuccessKillBackend(String userPhone, long productId) {
        //更新用户订单状态
        successKilledMapper.updateByPrimaryKeySelective(
                new SuccessKilled(productId, Long.valueOf(userPhone), Byte.valueOf(0 + "")));
        //更新产品订单库存
        seckillMapper.updateNumber(productId);
    }
}
