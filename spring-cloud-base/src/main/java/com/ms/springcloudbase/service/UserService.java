package com.ms.springcloudbase.service;

import com.ms.springcloudbase.bean.User;

/**
 * @创建人 Oliver.Liu
 * @创建时间 4/10/2019
 * @描述
 */
public interface UserService {
    User selectByPrimaryKey(Integer id);
}
