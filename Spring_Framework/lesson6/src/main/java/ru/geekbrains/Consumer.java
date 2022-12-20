package ru.geekbrains;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "consumers")
public class Consumer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "consumers_products",
            joinColumns = @JoinColumn(name = "consumer_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getConsumers() {
        return products;
    }

    public void setConsumers(List<Product> products) {
        this.products = products;
    }

    public Consumer() {
    }

    @Override
    public String toString() {
        return String.format("Reader [id = %d, name = %s]", id, name);
    }
}