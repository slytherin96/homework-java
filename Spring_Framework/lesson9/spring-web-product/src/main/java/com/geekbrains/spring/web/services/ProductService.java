package com.geekbrains.spring.web.services;

import com.geekbrains.spring.web.entities.Product;
import com.geekbrains.spring.web.dto.ProductDto;
import com.geekbrains.spring.web.exceptions.ResourceNotFoundException;
import com.geekbrains.spring.web.repositories.ProductRepository;
import com.geekbrains.spring.web.repositories.specifications.ProductsSpecifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public Page<Product> find(Integer minCost, Integer maxCost, String partTitle, Integer page) {

        Specification<Product> spec = Specification.where(null);
        if (minCost != null) {
            spec = spec.and(ProductsSpecifications.scoreGreaterOrEqualsThan(minCost));
        }
        if (maxCost != null) {
            spec = spec.and(ProductsSpecifications.scoreLessThanOrEqualsThan(maxCost));
        }
        if (partTitle != null) {
            spec = spec.and(ProductsSpecifications.nameLike(partTitle));
        }
        return productRepository.findAll(spec, PageRequest.of(page - 1, 5));
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public ProductDto save(ProductDto productDto) {
        Product product = new Product(productDto);
        return new ProductDto(productRepository.save(product));
    }
    @Transactional
    public void changeScore(Long productId, Integer delta) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Unable to change product's cost. Product not found, id: " + productId));
        product.setCost(product.getCost()+delta);
    }
    public List<Product> findByScoreBetween(Integer min, Integer max) {
        return productRepository.findAllByCostBetween(min, max);
    }

}
