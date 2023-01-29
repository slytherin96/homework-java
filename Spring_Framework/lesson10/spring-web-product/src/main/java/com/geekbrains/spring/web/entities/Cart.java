package com.geekbrains.spring.web.entities;

import com.geekbrains.spring.web.dto.ProductDto;
import jakarta.persistence.Entity;
import org.springframework.stereotype.Component;

@Component
public class Cart {

    private Long id;

    private String title;

    private Integer cost;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Cart() {
    }

    public Cart(ProductDto productDto) {
        this.id = productDto.getId();
        this.title = productDto.getTitle();
        this.cost = productDto.getCost();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", cost=" + cost +
                '}';
    }
}
