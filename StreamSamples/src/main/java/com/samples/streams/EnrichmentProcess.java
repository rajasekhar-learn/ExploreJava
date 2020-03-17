package com.samples.streams;

import java.util.HashMap;
import java.util.Map;

public class EnrichmentProcess {

   private EnrichUtility enrichUtility=new EnrichUtility();

    EnrichmentProcess(EnrichUtility enrichUtility){
        this.enrichUtility=enrichUtility;
    }

    public Product enrich(Product product){
        long startTime=System.currentTimeMillis();
        String id=product.getId();
        product.setId(id+"enriched");
        Map<String,Object> data=new HashMap<>();
        data.put("enrichStartTime",startTime);
        data.put("enrichStatus","success".getBytes());
        product.setData(data);
        EnrichUtility.auditTrack(product);
        return product;
    }


}
