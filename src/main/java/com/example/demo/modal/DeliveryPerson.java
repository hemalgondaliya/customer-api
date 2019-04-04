package com.example.demo.modal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "delivery")
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
    String name;

    @Column(name = "phone_no",columnDefinition = "BIGINT")
    Long phoneNumber;

    @Column(name = "address",columnDefinition = "VARCHAR(50)")
    String address;

    @Column(name = "tempo_no",columnDefinition = "VARCHAR(50)")
    String tempoNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
