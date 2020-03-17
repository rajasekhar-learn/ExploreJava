package com.samples.streams;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
@Builder
public class Product implements Serializable {
    private String name;
    private String id;
    private Double price;
    private int location;
    private String description;
    private Map<String,Object> data;

    public Product(String name, String id, Double price, int location, String description) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.location = location;
        this.description = description;
    }
    public Product(String name, String id, Double price, int location, String description,Map<String,Object> data) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.location = location;
        this.description = description;
        this.data=data;
    }
}
