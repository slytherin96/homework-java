package com.geekbrains.spring.web.services;

import com.geekbrains.spring.web.data.Product;
import com.geekbrains.spring.web.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
    public void addById(Long id, String title, Double cost) {
        productRepository.addById(id, title, cost);
    }
    public void changeScore(Long studentId, Integer delta) {
        Product product = productRepository.findById(studentId);
        product.setCost(product.getCost() + delta);
    }
}
