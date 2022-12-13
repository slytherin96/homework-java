package com.geekbrains.spring.web.repositories;

import com.geekbrains.spring.web.data.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class ProductRepository {
    private List<Product> products;

    @PostConstruct
    public void init() {
        products = new ArrayList<>(Arrays.asList(
                new Product(1L, "milk", 35.60),
                new Product(2L, "cheese", 50.0),
                new Product(3L, "sausage", 60.0),
                new Product(4L, "ketchup", 70.0),
                new Product(5L, "mayonnaise", 100.0)
        ));
    }

    public List<Product> getAllProducts() {
        return Collections.unmodifiableList(products);
    }

    public void deleteById(Long id){
        products.removeIf(s -> s.getId().equals(id));
    }

    public void addById(Long id, String title, Double cost){
        if (products.stream().filter(p -> p.getId().equals(id)).findFirst().isEmpty()) {
            products.add(new Product(id, title, cost));
        }
    }

    public Product findById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst().orElseThrow(() -> new RuntimeException());
    }
}
