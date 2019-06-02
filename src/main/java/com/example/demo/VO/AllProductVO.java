package com.example.demo.VO;

import com.example.demo.modal.ProductModel;

public class AllProductVO {

    String name;

    public AllProductVO(ProductModel p) {
        this.name = p.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
