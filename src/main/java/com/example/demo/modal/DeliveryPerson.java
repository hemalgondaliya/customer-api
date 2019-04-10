package com.example.demo.modal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "delivery_person")
public class DeliveryPerson {
    @Id
    @GeneratedValue(generator = "delivery_id_generator")
    @SequenceGenerator(
            name = "delivery_id_generator",
            sequenceName = "number_sequence",
            initialValue = 1
    )
    private Long id;

    @Column(name = "name", columnDefinition = "VARCHAR(50)")
    @NotNull(message = "Delivery person name can not be null")
    String name;

    @Column(name = "phone_no",columnDefinition = "BIGINT")
    @NotNull(message = "Phone number can not be null")
    Long phoneNumber;

    @Column(name = "address",columnDefinition = "VARCHAR(50)")
    @NotNull(message = "Address can not be null")
    String address;

    @Column(name = "tempo_no",columnDefinition = "VARCHAR(50)")
    @NotNull(message = "Tempo number can not be null")
    String tempoNumber;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTempoNumber() {
        return tempoNumber;
    }

    public void setTempoNumber(String tempoNumber) {
        this.tempoNumber = tempoNumber;
    }
}
