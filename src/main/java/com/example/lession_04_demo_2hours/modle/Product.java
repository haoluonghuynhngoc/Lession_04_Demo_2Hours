package com.example.lession_04_demo_2hours.modle;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //unique = true không được phép giông nhau
    @Column(nullable = false,unique = true,length = 300)
    private String productName;
    @Column
    private int year;
    @Column
    private Double price;
    @Column
    private String url;

    public Product(String productName, int year, Double price, String url) {
        this.productName = productName;
        this.year = year;
        this.price = price;
        this.url = url;
    }
}
