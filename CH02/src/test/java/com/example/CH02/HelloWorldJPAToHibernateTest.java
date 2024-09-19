package com.example.CH02;

import org.hibernate.SessionFactory;

import javax.persistence.EntityManagerFactory;

public class HelloWorldJPAToHibernateTest {
    private static SessionFactory sessionFactory(
            EntityManagerFactory emf
    ){
        return emf.unwrap(SessionFactory.class);
    }

}
