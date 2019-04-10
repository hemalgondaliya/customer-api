package com.example.demo.modal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "brand")
public class Brand {

    @Id
    @GeneratedValue(generator = "cust_id_generator")
    @SequenceGenerator(
            name = "brand_id_generator",
            sequenceName = "number_sequence",
            initialValue = 1
    )
    private Long id;

    @Column(name = "name", columnDefinition = "VARCHAR(50)")
    @NotNull(message = "Brand name can not be null")
    private String name;

    @Column(name = "category", columnDefinition = "VARCHAR(50)")
    @NotNull(message = "Brand category number can not be null")
    private String category;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}