package com.geekbrains.spring.web.controllers;

import com.geekbrains.spring.web.dto.ProductDto;
import com.geekbrains.spring.web.entities.Cart;
import com.geekbrains.spring.web.entities.Product;
import com.geekbrains.spring.web.exceptions.ResourceNotFoundException;
import com.geekbrains.spring.web.services.CartService;
import com.geekbrains.spring.web.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/carts")
public class CartController {
    private CartService cartService;
    private ProductService productService;

    public CartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @PostMapping
    public void addProductInCart(@RequestBody ProductDto productDto) {
        cartService.saveProductInCart(productDto);
    }
 /*   @GetMapping("/{id}")
    public void getProductById(@PathVariable Long id) {
        cartService.saveProductInCart(new ProductDto(productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found, id: " + id))));
    }*/

    @GetMapping
    public List<Cart> findAll(){
        return cartService.findAll();
    }

    @DeleteMapping ("/{id}")
    public void deleteById(@PathVariable Long id) {
        cartService.deleteCart(id);
    }

}

