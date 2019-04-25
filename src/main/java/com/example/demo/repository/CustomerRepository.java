package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.modal.Customer;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("select c from Customer c where c.billNumber = :billNumber and FUNCTION('YEAR', c.date) = :year")
    List<Customer> getByBillNumberAndYear(int billNumber, int year);

}


