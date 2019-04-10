package com.example.demo.repository;

import com.example.demo.modal.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductModelRepository extends JpaRepository<ProductModel, Long> {
    public ProductModel findByName(String name);
    public List<ProductModel> findAllByName(List<String> modelList);
}
