package com.example.lession_04_demo_2hours.controller;

import com.example.lession_04_demo_2hours.modle.Product;
import com.example.lession_04_demo_2hours.modle.ResponseObject;
import com.example.lession_04_demo_2hours.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/Product")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("")
    public List<String> getString() {
        return List.of("iphone", "ipad");
    }

    //    @GetMapping("/products")
//    public List<Product> getAllProduct() {
//        return List.of(new Product(1l, "Macbook pro 16 inch", 2022, 240.1, ""),
//                new Product(1l, "Iphone pro 16 inch", 2018, 240.1, ""),
//                new Product(2l, "Samsung pro 16 inch", 2012, 23.1, ""),
//                new Product(3l, "Vertu ", 2012, 2230.1, ""),
//                new Product(4l, "Nokia ", 2022, 240.1, ""));
//    }
    @GetMapping("/products")
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> findById(@PathVariable("id") Long id) {
        Optional<Product> foundProduct = productRepository.findById(id);
        if (foundProduct.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Query product successfully", foundProduct)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("false", "cannot find product with id = " + id, "")
            );
        }
    }

    // chưa chạy hay test thử lần nào
    @PostMapping("/insert")
    public ResponseEntity<ResponseObject> insertProduct(@RequestBody Product product) {
        List<Product> foundNameProduct = productRepository.findProductByProductName(product.getProductName().trim());
        if (foundNameProduct.size() > 0) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("faild", "Product name alrady taken", "")
            );
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK", "Insert Product Successfully", productRepository.save(product))
            );
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateproduct(@RequestBody Product newProduct, @PathVariable Long id) {
        Product updateProduct = productRepository.findById(id).map(product -> { // ánh xạ qua class product xem kỹ hơn tại phút thứ 45:57
            product.setProductName(newProduct.getProductName());
            product.setYear(newProduct.getYear());
            product.setPrice(newProduct.getPrice());
            return productRepository.save(product);
        }).orElseGet(() -> {
            newProduct.setId(id);
            return productRepository.save(newProduct);
        });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "update product successfully", updateProduct)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteProduct(@PathVariable("id") Long id) {
        boolean exists = productRepository.existsById(id);
        if (exists) {
            productRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK", "Delete product Succsessfully", "")

            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Cannot find product to delete", "")
            );
        }
    }
}
