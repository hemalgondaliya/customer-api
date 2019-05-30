package com.example.demo.modal;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "product_model")
public class ProductModel {

    @Id
    @GeneratedValue(generator = "product_model_id_generator")
    @SequenceGenerator(
            name = "product_model_id_generator",
            sequenceName = "product_model_number_sequence",
            allocationSize = 1
    )
    private Long id;

    @Column(name = "name", columnDefinition = "VARCHAR(50)")
    @NotBlank(message = "Model name can not be blank")
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
