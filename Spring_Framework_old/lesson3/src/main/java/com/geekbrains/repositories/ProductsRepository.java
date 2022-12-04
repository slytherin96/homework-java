package com.geekbrains.repositories;

import com.geekbrains.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductsRepository {
    public Product findOneById(int id, String title, double cost) {
        Product product = new Product();
        product.setId(id);
        product.setTitle(title);
        product.setCost(cost);
        return product;
    }
}
