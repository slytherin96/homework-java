package com.geekbrains.spring.web.services;

import com.geekbrains.spring.web.dto.ProductDto;
import com.geekbrains.spring.web.entities.Cart;
import com.geekbrains.spring.web.repositories.CartRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CartService {
    private CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void saveProductInCart (ProductDto productDto){
        cartRepository.saveProductInCart(productDto);
    }
    public void deleteCart (Long id){
        cartRepository.deleteCart(id);
    }

    public List<Cart> findAll (){
        return cartRepository.findAll();
    }

}
