package com.ms.springcloudbase.dao;

import com.ms.springcloudbase.bean.Seckill;

import java.util.List;

public interface SeckillMapper {
    int deleteByPrimaryKey(Long seckillId);

    int insert(Seckill record);

    int insertSelective(Seckill record);

    Seckill selectByPrimaryKey(Long seckillId);

    int updateByPrimaryKeySelective(Seckill record);

    int updateByPrimaryKey(Seckill record);
    //更新库存，减一
    int updateNumber(Long seckillId);

    List<Seckill> selectAll();
}