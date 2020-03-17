package com.samples.streams;

public class Producer {
    SharedResource sharedResource;
    public void produce(){
        sharedResource.increment();
    }
}
