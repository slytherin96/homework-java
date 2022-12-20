package ru.geekbrains;

import java.util.List;

public interface ProductDao {

    Product findById(Long id);

    List<Product> findAll();

    void updateCostById(Long id, Integer newCost);

    void deleteById(Long id);
    void save(String title, Integer cost);

    List<Product> getProductsInConsumer(Long id);
}
