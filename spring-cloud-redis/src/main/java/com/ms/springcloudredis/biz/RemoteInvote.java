package com.ms.springcloudredis.biz;


import java.util.ArrayList;
import java.util.List;

public class RemoteInvote {

    public void test1() {
        List<Long> list = new ArrayList();
        for(long i = 1; i <= 10000; i++){
            list.add(i);
        }
        list.stream().forEach(o -> {
            String url = "http://localhost:8000/seckill/product/1000";
            String s1=HttpRequest.sendGet(url, "userId="+o);
            System.out.println(s1);
        });
    }

}
