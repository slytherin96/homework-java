package com.geekbrains.spring.web.repositories;

import com.geekbrains.spring.web.dto.ProductDto;
import com.geekbrains.spring.web.entities.Cart;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class CartRepository {
    private List<Cart> carts;

    @PostConstruct
    public void init() {
        carts = new ArrayList<>();
    }

    public void saveProductInCart (ProductDto productDto){
        carts.add(new Cart(productDto));

    }

    public List<Cart> findAll (){
        return Collections.unmodifiableList(carts);
    }

    public void deleteCart (Long id){
        carts.remove(carts.stream().filter(c -> c.getId().equals(id)).findFirst().orElseThrow(() -> new RuntimeException()));
    }
}
