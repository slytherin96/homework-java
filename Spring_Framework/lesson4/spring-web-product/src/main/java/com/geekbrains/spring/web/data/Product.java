package com.geekbrains.spring.web.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Product {

    private Long id;
    private String title;
    private Double cost;
}
