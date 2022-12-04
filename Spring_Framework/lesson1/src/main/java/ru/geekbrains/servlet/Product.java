package ru.geekbrains.servlet;
import lombok.Data;

@Data
public class Product {

    private final int id;
    private final String title;
    private final double cost;
}
