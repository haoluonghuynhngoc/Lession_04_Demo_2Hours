package com.example.lession_04_demo_2hours.database;

import com.example.lession_04_demo_2hours.modle.Product;
import com.example.lession_04_demo_2hours.repository.ProductRepository;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Logger;

@Configuration
public class Database {
  //  private static final Logger logger = (Logger) LoggerFactory.getLogger(Database.class);

    @Bean
    CommandLineRunner initDatabse(ProductRepository productRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Product productA = new Product( "Iphone pro 16 inch", 2018, 240.1, "");
                Product productB = new Product( "Samsung pro 16 inch", 2012, 23.1, "");
                Product productC = new Product( "Vertu ", 2012, 2230.1, "");
                Product productD = new Product( "Nokia ", 2022, 240.1, "");
                productRepository.save(productA);
                productRepository.save(productB);
                productRepository.save(productC);
                productRepository.save(productD);
//                logger.info("Insert Product " + productRepository.save(productA));
//                logger.info("Insert Product " + productRepository.save(productB));
//                logger.info("Insert Product " + productRepository.save(productC));
//                logger.info("Insert Product " + productRepository.save(productD));
            }
        };
    }
}
