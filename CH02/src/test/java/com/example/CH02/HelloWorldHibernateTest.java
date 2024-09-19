package com.example.CH02;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelloWorldHibernateTest {
    private static SessionFactory sessionFactory(){
        var configuration = new Configuration().configure()
                .addAnnotatedClass(Message.class);
        var serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    @Test
    public void storeLoadMessage(){
        try(var sessionFactory = sessionFactory();
        var session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(new Message().setText("Hello World from Hibernate!"));
            session.getTransaction().commit();

            session.beginTransaction();
            var criteriaQuery = session.getCriteriaBuilder().createQuery(Message.class);
            criteriaQuery.from(Message.class);
            var messages = session.createQuery(criteriaQuery).getResultList();
            session.getTransaction().commit();

            assertAll(
                    ()->assertEquals(1,messages.size()),
                    ()->assertEquals("Hello World from Hibernate!",messages.getFirst().getText())
            );
        }
    }
}
