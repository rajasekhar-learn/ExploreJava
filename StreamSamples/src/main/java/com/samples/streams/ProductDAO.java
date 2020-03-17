package com.samples.streams;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

public class ProductDAO implements DAO<Product>{

    private volatile static Map<String,Product> products=new ConcurrentHashMap<>();
    //private static Semaphore semaphore=new Semaphore(1);

    @Override
    public Product read() {
        return products.size()>0?products.get(0):null;
    }

    @Override
    public void write(Product product) {
        try {
            //semaphore.acquire();
            synchronized (products) {
                System.out.println(Thread.currentThread().getId() + " adding product,  size :: " + products.size());
                if (products.size() == 100) {
                    Thread.sleep(1000);//wait for cleaning thread
                    products.put(product.getName() + product.getId(), product);
                } else {
                    products.put(product.getName() + product.getId(), product);
                }
            }
             //   semaphore.release();
        }catch (Exception e){
            System.out.println("i am sleeping thread(writing) , errMsg: "+e.getMessage());
        }
    }

    @Override
    public boolean remove(Product product) {
        return false;
    }

    @Override
    public void clear()  {
        try {
           // semaphore.acquire();
            synchronized (products) {
                while (true) {

                    if (!products.isEmpty() && products.size() >= 100) {
                        System.out.println(Thread.currentThread().getId() + "clearing  - size :: " + products.size());
                        products.clear();
                        //     semaphore.release();
                        break;
                    }
                }
            }
        }catch (Exception e){
            System.out.println("i am sleeping thread(cleaning) , errMsg: "+e.getMessage());
        }

    }

    public boolean isEmpty(){
        return products.isEmpty();
    }

    public long size(){
        return products.size();
    }
}
