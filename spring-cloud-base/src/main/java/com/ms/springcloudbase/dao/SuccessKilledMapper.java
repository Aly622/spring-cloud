package com.ms.springcloudbase.dao;

import com.ms.springcloudbase.bean.SuccessKilled;
import com.ms.springcloudbase.bean.SuccessKilledKey;

public interface SuccessKilledMapper {
    int deleteByPrimaryKey(SuccessKilledKey key);

    int insert(SuccessKilled record);

    int insertSelective(SuccessKilled record);

    SuccessKilled selectByPrimaryKey(SuccessKilledKey key);

    int updateByPrimaryKeySelective(SuccessKilled record);

    int updateByPrimaryKey(SuccessKilled record);
}