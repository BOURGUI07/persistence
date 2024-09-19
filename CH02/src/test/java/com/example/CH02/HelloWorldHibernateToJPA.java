package com.example.CH02;

import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class HelloWorldHibernateToJPA {

    private static EntityManagerFactory createEntityManagerFactory() {
        var configuration = new Configuration().configure().addAnnotatedClass(Message.class);
        Map<String,String> properties = new HashMap<>();
        Enumeration<?> propertyNames = configuration.getProperties().propertyNames();
        while(propertyNames.hasMoreElements()) {
            String propertyName = (String) propertyNames.nextElement();
            properties.put(propertyName, configuration.getProperty(propertyName));
        }
        return Persistence.createEntityManagerFactory("ch02", properties);
    }
}
