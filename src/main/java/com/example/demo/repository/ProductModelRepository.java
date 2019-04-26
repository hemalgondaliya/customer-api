package com.example.demo.repository;

import com.example.demo.modal.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductModelRepository extends JpaRepository<ProductModel, Long> {
    ProductModel findByName(String name);
    List<ProductModel> findAllByName(List<String> modelList);
}
