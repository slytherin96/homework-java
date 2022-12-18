package com.geekbrains.spring.web.services;

import com.geekbrains.spring.web.Product;
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
    public void addById(String title, Integer cost) {
        productRepository.addById(title, cost);
    }
    public void changeScore(Long productId, Integer delta) {
        Product product = productRepository.findById(productId);
        Integer newCost = product.getCost() + delta;
        product.setCost(newCost);
        productRepository.updateCostById(productId,newCost);
    }
}
