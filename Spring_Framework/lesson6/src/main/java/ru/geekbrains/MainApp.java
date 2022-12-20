package ru.geekbrains;

import org.hibernate.Session;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("ru.geekbrains");
        SessionFactoryUtils sessionFactoryUtils = context.getBean(SessionFactoryUtils.class);
        sessionFactoryUtils.init();


        ConsumerDaoImpl сonsumerDaoImpl = context.getBean(ConsumerDaoImpl.class);
        System.out.println(сonsumerDaoImpl.findAll());

        ProductDaoImpl productDaoImpl = context.getBean(ProductDaoImpl.class);
        Long idConsumer = 1L;
        System.out.println(сonsumerDaoImpl.findById(idConsumer).getName()+": "+productDaoImpl.getProductsInConsumer(idConsumer));
        Long idProduct = 2L;
        System.out.println(productDaoImpl.findById(idProduct).getTitle()+": "+сonsumerDaoImpl.getConsumersInProduct(idProduct));

    }
}

