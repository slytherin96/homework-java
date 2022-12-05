package ru.geekbrains.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Cart {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProduct(Long id, String title, double cost ){
        productRepository.getProducts().add(new Product(id, title, cost));
        System.out.println(productRepository.getProducts().toString());
    }

    public void delProduct(Long id ){
        int index = productRepository.getProducts().indexOf(productRepository.getProducts(id));
        productRepository.getProducts().remove(index);
        System.out.println(productRepository.getProducts().toString());
    }


}
