package com.example.demo.modal;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(generator = "product_id_generator")
    @SequenceGenerator(name = "product_id_generator",
            sequenceName = "product_number_sequence",
            initialValue = 1
    )
    private Long id;

    @OneToOne
    @JoinColumn(name = "product_model_id")
    @NotNull(message = "Product model can not be null")
    ProductModel productModel;

    @Column(name = "ser_no", columnDefinition = "VARCHAR(50)")
    String serialNumber;

    @Column(name = "price", columnDefinition = "INTEGER")
    @NotNull(message = "Price for product can not be null")
    Integer price;

    public Long getId() {
        return id;
    }

    public ProductModel getProductModel() {
        return productModel;
    }

    public void setProductModel(ProductModel productModel) {
        this.productModel = productModel;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
