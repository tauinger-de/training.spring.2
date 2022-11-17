package jpa.d_with_spring;

import jpa.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;

@Component
public class LogicRunner implements CommandLineRunner {

    private EntityManager entityManager;

    @Autowired
    TransactionTemplate transactionTemplate;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void run(String... args) throws Exception {
        transactionTemplate.executeWithoutResult(x -> {
            entityManager.persist(
                    new Customer("Enrico Pallazzo 100d", LocalDate.of(1966, 2, 3))
            );
        });
    }
}
