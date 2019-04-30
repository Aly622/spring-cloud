package com.ms.springcloudbase.pool;

import com.ms.springcloudbase.pojo.QueueEntity;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 */
public class ExecutorPool {

    public static ConcurrentLinkedQueue<QueueEntity> queue = new ConcurrentLinkedQueue<>();

    public static ExecutorService executorService = Executors.newCachedThreadPool();

    public static void submit(Runnable task){
        executorService.submit(task);
    }
}
