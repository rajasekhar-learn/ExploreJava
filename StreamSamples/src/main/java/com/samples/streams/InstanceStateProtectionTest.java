package com.samples.streams;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class InstanceStateProtectionTest {
    public static ProductDAO productDAO=new ProductDAO();
    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService= Executors.newCachedThreadPool();
        IntStream.rangeClosed(1,1000).forEach(i->{
            System.out.println("Writing "+Thread.currentThread().getId()+" - size :: "+productDAO.size());
            executorService.submit(()->{
                try {
                    productDAO.write(getRandomProduct());
                }catch (Exception e){
                    System.out.println("i am sleeping thread(writing) , errMsg: "+e.getMessage());
                }
            });
        });
        Thread.sleep(2000);
        while (true){
            try {
               // System.out.println("main thread :: "+productDAO.size());
                if(!productDAO.isEmpty() && productDAO.size() >= 100) {
                    System.out.println("Cleaning " + Thread.currentThread().getId() + " - size :: " + productDAO.size());
                    executorService.submit(productDAO::clear);
                }
                Thread.sleep(2000);
                if(productDAO.size()==0){
                    break;
                }
            }catch (Exception e){
                System.out.println("i am sleeping thread(cleaning) , errMsg: "+e.getMessage());
            }
        }
        executorService.shutdown();
        System.out.println("final size :: "+productDAO.size());
        //executorService.shutdown();
    }
    public static Product getRandomProduct(){
        System.out.println("getRandomProduct "+Thread.currentThread().getId());
        return Product.builder().id(String.valueOf(ThreadLocalRandom.current().nextInt())).name(UUID.randomUUID().toString()).build();
    }

}
