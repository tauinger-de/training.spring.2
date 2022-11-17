package jpa.d_with_spring;

import core.h2.H2Server;
import core.h2.SchemaScriptRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;

@Configuration
public class TrxLessPersistenceConfiguration {

    @Bean
    public CommandLineRunner schemaScriptRunner() {
        return new SchemaScriptRunner(dataSource(), h2Server());
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    public H2Server h2Server() {
        return new H2Server().setPort(9092);
    }

    @Bean
    @DependsOn("h2Server")
    public DataSource dataSource() {
        return new DriverManagerDataSource("jdbc:h2:tcp://localhost:9092/~/training.spring.100-jpa");
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        return Persistence.createEntityManagerFactory("100-jpa");
    }

}
