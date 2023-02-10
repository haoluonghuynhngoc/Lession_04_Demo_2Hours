package com.example.lession_04_demo_2hours.service.impl;

import com.example.lession_04_demo_2hours.modle.Product;
import com.example.lession_04_demo_2hours.repository.ProductRepository;
import com.example.lession_04_demo_2hours.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(){
       // productRepository.
        return null;
    }
}
