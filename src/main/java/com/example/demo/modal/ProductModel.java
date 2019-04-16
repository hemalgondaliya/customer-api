package com.example.demo.modal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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

    @Column(name = "name", columnDefinition = "VARCHAR(50)", unique = true)
    @NotNull(message = "Model name can not be null")
    private String name;

    @ManyToOne
    @NotNull(message = "Model brand can not be null")
    @JoinColumn(name = "brand_id")
    Brand brand;

    @Column(name = "deprecated", columnDefinition = "BOOLEAN")
    @NotNull(message = "deprecated can not be null")
    private boolean deprecated;

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

    public boolean isDeprecated() {
        return deprecated;
    }

    public void setDeprecated(boolean deprecated) {
        this.deprecated = deprecated;
    }
}
