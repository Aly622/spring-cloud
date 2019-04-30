package com.ms.springcloudbase.pool;

import com.ms.springcloudbase.pojo.QueueEntity;
import com.ms.springcloudbase.service.SuccesskilledService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by feibabm on 2017/9/11 0011.
 */
@Service
public class BackendUpdateNumberTask implements Runnable{

    @Autowired
    private SuccesskilledService successkilledService;

    @Override
    public void run() {
        while (true){
            if(!ExecutorPool.queue.isEmpty()){
                QueueEntity poll = ExecutorPool.queue.poll();
                try{
                    successkilledService.saveSuccessKillBackend(poll.getUserPhone(), poll.getProductId());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

    }


}
