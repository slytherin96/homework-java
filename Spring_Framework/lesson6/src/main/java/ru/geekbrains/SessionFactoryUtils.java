package ru.geekbrains;

import jakarta.annotation.PostConstruct;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SessionFactoryUtils {

    private SessionFactory factory;

   SessionFactoryUtils (){
       factory = new Configuration()
               .configure("hibernate.cfg.xml")
               .buildSessionFactory();
   }

    public Session getSession() {
        return factory.getCurrentSession();
    }

    public void shutdown() {
        if (factory != null) {
            factory.close();
        }
    }
}