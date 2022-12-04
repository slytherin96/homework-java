package com.geekbrains.service;

import com.geekbrains.entities.Product;
import com.geekbrains.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductsService {
    private ProductsRepository productsRepository;

    @Autowired
    public void setStudentsRepository(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public Product getStudentById(int id, String title, double cost) {
        return productsRepository.findOneById(id, title, cost);
    }

    public ProductsService() {
    }
}
