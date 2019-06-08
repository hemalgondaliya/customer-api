package com.example.demo.vo;

import java.util.List;

public class ProductVO {

    private String model;
    private List<String> serialNumber;
    private Integer price;
    private Integer qty;


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<String> getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(List<String> serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
}
