package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.modal.Brand;

public interface BrandRepositiry extends JpaRepository<Brand, Long> {
    public Brand findByNameAndCategory(String name, String category);
}
