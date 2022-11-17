package jpa.a_simple;

import jpa.Customer;
import jpa.PersistenceConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;

@SpringBootApplication
@Import(PersistenceConfiguration.class)
public class Main100a {

    public static void main(String[] args) {
        var context = SpringApplication.run(Main100a.class);

        EntityManagerFactory emf = context.getBean(EntityManagerFactory.class);

//        var resultList = emf.createEntityManager().createNativeQuery("SHOW TABLES").getResultList();
//        resultList.forEach(r -> System.out.println(r));

        var entityManager = emf.createEntityManager();
        var trx = entityManager.getTransaction();
        trx.begin();
        entityManager.persist(
                new Customer("Enrico Pallazzo", LocalDate.of(1966, 2, 3))
        );
        trx.commit();
    }

}
