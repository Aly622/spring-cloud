package com.ms.springcloudkafka;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ms.springcloudbase.pojo.MessageObj;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class KafkaProducer {
    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;
    @Value("${kafka.topic.order}")
    private String topicOrder;
    public void sendMessage(MessageObj messageBean) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        builder.setDateFormat("yyyy-MM-dd HH:mm:ss");
        Gson gson = builder.create();
        //将消息实例化为json格式的字符串
        String message = gson.toJson(messageBean);
        kafkaTemplate.send(topicOrder, message);
        log.info("miniooc send message:"+message);
    }
}
