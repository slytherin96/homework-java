package ru.geekbrains;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ConsumerDaoImpl implements ConsumerDao{
    private SessionFactoryUtils sessionFactoryUtils;
    private List<Consumer> consumers;


    public ConsumerDaoImpl(SessionFactoryUtils sessionFactoryUtils) {

        this.sessionFactoryUtils = sessionFactoryUtils;
    }


    @Override
    public Consumer findById(Long id) {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            Consumer consumer = session
                    .createQuery("select c from Consumer c where c.id = :id", Consumer.class)
                    .setParameter("id", id)
                    .getSingleResult();
            session.getTransaction().commit();
            return consumer;
        }
    }

    @Override
    public List<Consumer> findAll() {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            consumers = session.createQuery("select p from Consumer p").getResultList();
            session.getTransaction().commit();
            return consumers;
        }
    }

    @Override
    public void save(String name) {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            session.createNativeQuery("INSERT INTO consumers (mame) VALUES (:name)")
                    .setParameter("title",name)
                    .executeUpdate();
            session.getTransaction().commit();
        }

    }

    @Override
    public List<Consumer> getConsumersInProduct(Long id) {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            List<Consumer> consumers = session.createQuery("SELECT c FROM Consumer c join fetch c.products p where p.id = :id")
                    .setParameter("id", id)
                    .getResultList();
            session.getTransaction().commit();
            return consumers;
        }
    }


    public void deleteById(Long id){
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            session.createQuery("delete from ru.geekbrains.Consumer p where p.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            session.getTransaction().commit();
        }
    }
}
