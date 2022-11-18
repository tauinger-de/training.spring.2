package jpa.a_without_spring;

import core.h2.H2Server;
import jpa.Customer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.persistence.Persistence;
import java.time.LocalDate;

public class Main100a {

    public static void main(String[] args) throws InterruptedException {
        // start database
        var h2Server = new H2Server().setPort(9092).start();

        // data source and script run
        var dataSource = new DriverManagerDataSource("jdbc:h2:tcp://localhost:9092/~/training.spring.100-jpa");
        h2Server.executeScript(dataSource, new ClassPathResource("schema.sql"));

        //
        var entityManagerFactory = Persistence.createEntityManagerFactory("100-jpa");

        var entityManager = entityManagerFactory.createEntityManager();
        var trx = entityManager.getTransaction();
        trx.begin();

        entityManager.persist(
                new Customer("Enrico Pallazzo", LocalDate.of(1966, 2, 3))
        );
        entityManager.persist(
                new Customer("Niko Newborn", LocalDate.now())
        );

        var resultList = entityManager.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
        resultList.forEach(System.out::println);

        trx.commit();

        // wait so we can check database content with tool
        Thread.sleep(60000);

        // stop database
        h2Server.stop();
    }

}
