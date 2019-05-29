package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.modal.DeliveryPerson;
import com.example.demo.modal.Response;
import com.example.demo.repository.DeliveryPersonRepository;

@Service
public class DeliveryPersonService {

    @Autowired
    private DeliveryPersonRepository deliveryPersonRepository;

    public List<String> getAllDeliveryPersonName() {
        List<DeliveryPerson> deliveryPeople = deliveryPersonRepository.findAll();
        return deliveryPeople.stream().map(c -> c.getName()).collect(Collectors.toList());
    }

    public ResponseEntity saveDeliveryPerson(DeliveryPerson deliveryPerson) {
        DeliveryPerson res = deliveryPersonRepository
                .findByPhoneNumber(deliveryPerson.getPhoneNumber());
        if (res != null) {
            return new ResponseEntity<>(new Response("Delivery Person already exists", 400), HttpStatus.BAD_REQUEST);
        }
        deliveryPersonRepository.save(deliveryPerson);
        return new ResponseEntity<>(new Response("Delivery Person saved", 200), HttpStatus.OK);
    }
}
