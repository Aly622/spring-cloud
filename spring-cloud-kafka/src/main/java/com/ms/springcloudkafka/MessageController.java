package com.ms.springcloudkafka;

import com.ms.springcloudbase.pojo.MessageObj;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Controller
@RequestMapping("/message")
public class MessageController {
    @Resource
    private KafkaProducer kafkaProducer;
    @RequestMapping("create")
    @ResponseBody
    public Map<String, Object> create(){
        //创建消息
        MessageObj messageObj = new MessageObj();
        String uuid = UUID.randomUUID().toString();
        messageObj.setUuid(uuid);
        messageObj.setDate(new Date());
        //将消息发送到kafka
        kafkaProducer.sendMessage(messageObj);
        Map<String, Object> model = new HashMap<>();
        //返回成功信息
        model.put("resultCode", 1);
        model.put("resultMsg", "success");
        model.put("messageObj", messageObj);
        return model;
    }
}
