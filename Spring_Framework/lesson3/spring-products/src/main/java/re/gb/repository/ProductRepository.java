package re.gb.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import re.gb.model.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> products;

    @PostConstruct
    public void init() {
        products = new ArrayList<>(Arrays.asList(
                new Product(1L, "milk", 35.60),
                new Product(2L, "cheese", 50),
                new Product(3L, "sausage", 60),
                new Product(4L, "ketchup", 70),
                new Product(5L, "mayonnaise", 100)
        ));
    }

    public List<Product> getProducts() {
        return products;
    }

    public Product getProducts(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst().orElseThrow(() -> new RuntimeException());
    }

}