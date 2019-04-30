package com.ms.springcloudredis.controller;

import com.ms.springcloudbase.bean.Seckill;
import com.ms.springcloudbase.pojo.CommonResponse;
import com.ms.springcloudbase.pojo.SecKillResult;
import com.ms.springcloudbase.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @创建人 Oliver.Liu
 * @创建时间 4/9/2019
 * @描述
 */
@RestController
@RequestMapping("/seckill")
public class SecKillController {

    @Autowired
    private SeckillService secKillService;

    @RequestMapping("/product/list")
    public Object getAllSecKill(){
        List<Seckill> allSecKill = secKillService.getAllSecKill();
        return CommonResponse.buildRespose4Success(allSecKill,"获得秒杀商品页成功");
    }

    //    @RequestMapping("/curenttime")
//    public Object getSystemDate(){
//        return CommonResponse.buildRespose4Success(System.currentTimeMillis(), "系统时间");
//    }
    @RequestMapping(value = "/product/{productId}")
    public Object secKillProduct(@RequestParam("userId") String userId, @PathVariable("productId") long  productId){
        SecKillResult secKillResult = secKillService.secKillProduct(userId, productId);
        return CommonResponse.buildRespose4Success(secKillResult, "处理成功");
    }
}
