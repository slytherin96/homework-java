package ru.geekbrains;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDaoImpl implements ProductDao {
    private SessionFactoryUtils sessionFactoryUtils;
    private List<Product> products;


    public ProductDaoImpl(SessionFactoryUtils sessionFactoryUtils) {

        this.sessionFactoryUtils = sessionFactoryUtils;
    }


    @Override
    public Product findById(Long id) {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            Product product = session
                    .createQuery("select p from ru.geekbrains.Product p where p.id = :id", Product.class)
                    .setParameter("id", id)
                    .getSingleResult();
            session.getTransaction().commit();
            return product;
        }
    }

    @Override
    public List<Product> findAll() {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            products = session.createQuery("select p from ru.geekbrains.Product p").getResultList();
            session.getTransaction().commit();
            return products;
        }
    }

    @Override
    public void save(String title, Integer cost) {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            session.createNativeQuery("INSERT INTO Products (title, cost) VALUES (:title, :cost)")
                    .setParameter("title",title)
                    .setParameter("cost",cost)
                    .executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public List<Product> getProductsInConsumer(Long id) {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            List<Product> products = session.createQuery("SELECT p FROM Product p join fetch p.consumers c where c.id = :id")
                    .setParameter("id", id)
                    .getResultList();
            session.getTransaction().commit();
            return products;
        }
    }


    @Override
    public void updateCostById(Long id, Integer newCost) {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            session.createQuery("update ru.geekbrains.Product p set p.cost = :cost where p.id = :id")
                    .setParameter("cost", newCost)
                    .setParameter("id", id)
                    .executeUpdate();
            session.getTransaction().commit();
        }
    }

    public void deleteById(Long id){
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            session.createQuery("delete from ru.geekbrains.Product p where p.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            session.getTransaction().commit();
        }
    }

}
