package com.samples.streams;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class ThreadsTest {

    public static void main(String[] args) throws Exception{
        ExecutorService executorService= Executors.newFixedThreadPool(2);
        SharedResource sharedResource=new SharedResource();
        Producer producer=new Producer();
        producer.sharedResource=sharedResource;
        Consumer consumer=new Consumer();
        consumer.sharedResource=sharedResource;
        IntStream.rangeClosed(0,1000).boxed().forEach(value->{
            try {
                    executorService.submit(() -> {
                        try {

                                producer.produce();
                            System.out.println(Thread.currentThread().getId());
                                consumer.consume();

                        }catch (Exception e){
                            e.printStackTrace();
                        }
            });
            }catch (Exception e){
                e.printStackTrace();
            }

        });

        executorService.awaitTermination(3, TimeUnit.SECONDS);
        executorService.shutdown();
        //Thread.sleep(1000);
        System.out.println(producer.sharedResource.getOrderCount() + " "+consumer.sharedResource.getOrderCount());
    }



}
