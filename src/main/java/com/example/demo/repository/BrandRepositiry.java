package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modal.Brand;

@Repository
public interface BrandRepositiry extends JpaRepository<Brand, Long> {
    Brand findByNameAndCategory(String name, String category);
}
