package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.modal.DeliveryPerson;
import com.example.demo.repository.DeliveryPersonRepository;

@Service
public class DeliveryPersonService {

    @Autowired
    private DeliveryPersonRepository deliveryPersonRepository;

    public List<String> getAllDeliveryPersonName() {
        List<DeliveryPerson> deliveryPeople = deliveryPersonRepository.findAll();
        return deliveryPeople.stream().map(c -> c.getName()).collect(Collectors.toList());
    }
}
