package com.samples.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class ProductProcessor {
    private static List<Product> products=new CopyOnWriteArrayList<>();
    private EnrichmentProcess enrichmentProcess=new EnrichmentProcess(new EnrichUtility());
    private static volatile boolean allDone=false;
    public ProductProcessor(){

    }
    public ProductProcessor(EnrichmentProcess enrichmentProcess){
        this.enrichmentProcess=enrichmentProcess;
    }

    public  List<Product> processProducts(List<Product> productsList) {
        productsList.parallelStream().forEach(product -> {
            products.add(enrichmentProcess.enrich(product));
        });
        return products;
    }


    public static void main(String[] args) throws Exception{
        ProductProcessor productProcessor=new ProductProcessor();

        ExecutorService executorService= Executors.newFixedThreadPool(3);
        List<Future> results=new CopyOnWriteArrayList<>();
        IntStream.rangeClosed(1,100).forEach(i->
        results.add(executorService.submit(()-> {
            if(i%3==0){
                return productProcessor.processProducts(getCars());
            } else if (i%3==1) {
                return productProcessor.processProducts(getMobiles());
            }else {
                return productProcessor.processProducts(getLaptops());
            }
        })));
        Thread.sleep(2000);

        executorService.shutdown();
        Thread.sleep(4000);
        System.out.println(results.size());
        //products.forEach(System.out::println);
        System.out.println("total products size :: "+products.size());
    }


    public static List<Product> getMobiles(){
        List<Product> mobiles=new ArrayList<>();
        IntStream.rangeClosed(1,100).forEach(location->mobiles.add(ProductProcessor.getProduct("iPhone",location)));
        return mobiles;
    }

    public static List<Product> getLaptops(){
        List<Product> laptops=new ArrayList<>();
        IntStream.rangeClosed(1,100).forEach(location->laptops.add(ProductProcessor.getProduct("HP",location)));
        return laptops;
    }

    public static List<Product> getCars(){
        List<Product> cars=new ArrayList<>();
        IntStream.rangeClosed(1,100).forEach(location->cars.add(ProductProcessor.getProduct("Tesla",location)));
        return cars;
    }

    public static Product getProduct(String name,Integer location){
        return Product.builder().id(String.valueOf(location)).name(name).description("").location(location).build();
    }

}
