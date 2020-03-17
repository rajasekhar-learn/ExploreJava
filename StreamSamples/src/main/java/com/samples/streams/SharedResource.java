package com.samples.streams;

public final class SharedResource {
    private volatile int orderCount;

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }
    public void increment(){
        this.orderCount++;
    }
    public void decrement(){
        this.orderCount--;
    }
}
