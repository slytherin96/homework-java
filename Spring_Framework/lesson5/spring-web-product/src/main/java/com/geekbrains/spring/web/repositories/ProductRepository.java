package com.geekbrains.spring.web.repositories;

import com.geekbrains.spring.web.Product;
import com.geekbrains.spring.web.ProductDaoImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class ProductRepository {
    private List<Product> products;
    @Autowired
    private ProductDaoImpl productDaoImpl;

    @PostConstruct
    public void init() {

        products = productDaoImpl.findAll();
    }

    public List<Product> getAllProducts() {
        return Collections.unmodifiableList(products);
    }

    public void deleteById(Long id){
        products.removeIf(s -> s.getId().equals(id));
        productDaoImpl.deleteById(id);
        //products = productDaoImpl.findAll();

    }

    public void addById(String title, Integer cost){
        //products.add(new Product(id, title, cost));
        productDaoImpl.save(title, cost);
        products = productDaoImpl.findAll();

    }
    public void updateCostById(Long id, Integer newCost){
        productDaoImpl.updateCostById(id, newCost);
        //products = productDaoImpl.findAll();
    }
    public Product findById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst().orElseThrow(() -> new RuntimeException());
    }
}
