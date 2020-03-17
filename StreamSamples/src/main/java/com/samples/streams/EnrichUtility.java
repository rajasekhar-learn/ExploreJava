package com.samples.streams;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class EnrichUtility {

    public static void auditTrack(Product product){
        long utilityId=System.currentTimeMillis()+ ThreadLocalRandom.current().nextLong();
        System.out.println(utilityId+" - "+product.getId()+" - "+ Arrays.deepToString(product.getData().values().toArray()));
        product.getData().put("utilityIdentifier",utilityId);
    }

}
