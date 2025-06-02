package com.example.crudoperations.repository;

import com.example.crudoperations.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product,Integer> {

    Product findByName(String name);
}
