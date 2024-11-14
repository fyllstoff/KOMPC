package com.example.kompc;

public class Product {
    private String id;
    private String name;
    private String price;
    private String brand;
    private String details;
    private boolean isSold; //

    public Product() {

    }


    public Product(String id, String name, String price, String brand, String details, boolean isSold) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.details = details;
        this.isSold = isSold; //
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public boolean isSold() {
        return isSold;
    }

    public void setIsSold(boolean isSold) {
        this.isSold = isSold;
    }
}
