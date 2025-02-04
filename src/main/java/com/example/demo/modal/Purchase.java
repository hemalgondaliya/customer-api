package com.example.demo.modal;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "purchase")
public class Purchase {

    @Id
    @GeneratedValue(generator = "purchase_id_generator")
    @SequenceGenerator(name = "purchase_id_generator",
            sequenceName = "purchase_number_sequence",
            allocationSize = 1
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

    @Column(name = "qty", columnDefinition = "INTEGER")
    @NotNull(message = "QTY for product can not be null")
    Integer qty;

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

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
}
