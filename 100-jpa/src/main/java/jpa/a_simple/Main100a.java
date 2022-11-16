package jpa.a_simple;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@SpringBootApplication
public class Main100a {

    public static void main(String[] args) {
        var context = SpringApplication.run(Main100a.class);

        EntityManagerFactory emf = context.getBean(EntityManagerFactory.class);
        System.out.println(emf);
        var resultList = emf.createEntityManager().createNativeQuery("SHOW TABLES").getResultList();
    }

    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();

        factory.setDataSource(dataSource);
        factory.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        factory.setPersistenceXmlLocation("META-INF/persistence.xml");

        Properties properties = new Properties();
        properties.setProperty("javax.persistence.schema-generation.database.action", "create");
        factory.setJpaProperties(properties);

        return factory;
    }
}
