package com.geekbrains.spring.web;

import java.util.List;

public interface ProductDao {

    Product findById(Long id);

    List<Product> findAll();

    void updateCostById(Long id, Integer newCost);

    void deleteById(Long id);
    void save(String title, Integer cost);

}
