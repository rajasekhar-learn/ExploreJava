package com.samples.streams;

public class Consumer {
    SharedResource sharedResource;
    public void consume(){
        sharedResource.decrement();
    }
}
