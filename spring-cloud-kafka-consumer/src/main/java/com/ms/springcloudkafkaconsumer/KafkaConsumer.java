package com.ms.springcloudkafkaconsumer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.ms.springcloudbase.pojo.MessageObj;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaConsumer {
    @KafkaListener(topics = "${kafka.topic.order}", containerFactory = "kafkaListenerContainerFactory")
    public void consume(@Payload String message) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        builder.setDateFormat("yyyy-MM-dd HH:mm:ss");
        Gson gson = builder.create();
        // 将接收到的消息反序列化消息实例
        MessageObj messageBean = gson.fromJson(message, new TypeToken<MessageObj>() {
        }.getType());
        // 将消息实例序列化为json格式的字符串
        String json = gson.toJson(messageBean);
        // 打印消息
        log.info("\nminiooc receive message：\n" + json);
        System.out.println("\nminiooc receive message：\n" + json);
    }
}
