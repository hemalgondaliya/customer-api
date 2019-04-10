package com.example.demo.modal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "product_model")
public class ProductModel {

    @Id
    @GeneratedValue(generator = "product_model_id_generator")
    @SequenceGenerator(
            name = "product_model_id_generator",
            sequenceName = "number_sequence",
            initialValue = 1
    )
    private Long id;

    @Column(name = "name", columnDefinition = "VARCHAR(50)")
    private String name;

    @ManyToOne
    @JoinColumn(name = "brand_id",nullable = true)
    Brand brand;

    @Column(name = "depricated", columnDefinition = "BOOLEAN")
    private boolean depricated;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public boolean isDepricated() {
        return depricated;
    }

    public void setDepricated(boolean depricated) {
        this.depricated = depricated;
    }
}
