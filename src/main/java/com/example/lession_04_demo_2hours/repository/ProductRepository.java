package com.example.lession_04_demo_2hours.repository;

import com.example.lession_04_demo_2hours.modle.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findProductByProductName(String name);
}
