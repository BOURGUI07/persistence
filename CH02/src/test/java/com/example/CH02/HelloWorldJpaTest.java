package com.example.CH02;

import org.junit.jupiter.api.Test;

import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelloWorldJpaTest {
    @Test
    public void loadMessage(){
        var emf = Persistence.createEntityManagerFactory("ch02");
        try{
            var entityManager = emf.createEntityManager();
            entityManager.getTransaction().begin();
            var message = new Message().setText("Hello World!");
            entityManager.persist(message);
            entityManager.getTransaction().commit();

            entityManager.getTransaction().begin();
            var messages = entityManager.createQuery("select m from Message m", Message.class).getResultList();
            messages.get(messages.size() - 1).setText("Hello World from JPA!");
            entityManager.getTransaction().commit();
            assertAll(
                    ()-> assertEquals(1,messages.size()),
                    ()->assertEquals("Hello World from JPA!",messages.get(0).getText())
            );
            entityManager.close();
        }finally {
            emf.close();
        }
    }
}
