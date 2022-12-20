package ru.geekbrains;

import java.util.List;

public interface ConsumerDao {
    Consumer findById(Long id);
    List<Consumer> findAll();

    void deleteById(Long id);
    void save(String name);

    public List<Consumer> getConsumersInProduct(Long id);

}
