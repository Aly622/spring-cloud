package com.ms.springcloudconsumer01.feign;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @创建人 Oliver.Liu
 * @创建时间 4/1/2019
 * @描述
 */
@Slf4j
@RestController
public class ConsumerController {
    private int seq = 0;
    @Autowired
    private ConsumerClient consumerClient;

    @GetMapping(value = "/distributedlock")
    public String distributedlock() {
        seq++;
        log.info("consumer index:" + seq);
        System.out.println("call distributedlock" + seq);
        return  consumerClient.spikefeign();
    }
}
