package com.geekbrains.spring.web.controllers;

import com.geekbrains.spring.web.converters.ProductConverter;
import com.geekbrains.spring.web.entities.Product;
import com.geekbrains.spring.web.dto.ProductDto;
import com.geekbrains.spring.web.exceptions.ResourceNotFoundException;
import com.geekbrains.spring.web.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;


    @GetMapping
    public Page<ProductDto> getAllProducts(@RequestParam(name = "p", defaultValue = "1") Integer page,
                                           @RequestParam(name = "min_cost", required = false) Integer minCost,
                                           @RequestParam(name = "max_cost", required = false) Integer maxCost,
                                           @RequestParam(name = "title_part", required = false) String titlePart
    ) {
        if (page < 1) {
            page = 1;
        }

        return productService.find(minCost, maxCost, titlePart, page).map( s -> productConverter.entityToDto(s));
    }

    @DeleteMapping ("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PostMapping("/change_score")
    public void changeScore(@RequestParam Long productId, @RequestParam Integer delta) {
        productService.changeScore(productId, delta);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found, id: " + id));
    }
    @GetMapping("/cost_between")
    public List<Product> findProductsByCostBetween(@RequestParam(defaultValue = "0") Integer min, @RequestParam(defaultValue = "10000") Integer max) {
        return productService.findByScoreBetween(min, max);
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {

        return productService.save(productConverter.dtoToEntity(productDto));
    }

    @PostMapping
    public ProductDto saveNewProduct(@RequestBody ProductDto productDto) {
        productDto.setId(null);
        return productService.save(productConverter.dtoToEntity(productDto));
    }

}

