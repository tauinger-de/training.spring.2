package jpa.d_with_spring;

import jpa.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;

@Component
public class LogicRunner100d implements CommandLineRunner {

    private EntityManagerFactory entityManagerFactory;


    @Autowired
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void run(String... args) {
        var entityManager = entityManagerFactory.createEntityManager();
        var trx = entityManager.getTransaction();
        trx.begin();
        entityManager.persist(
                new Customer("Enrico Pallazzo 100d", LocalDate.of(1966, 2, 3))
        );
        trx.commit();
    }
}
