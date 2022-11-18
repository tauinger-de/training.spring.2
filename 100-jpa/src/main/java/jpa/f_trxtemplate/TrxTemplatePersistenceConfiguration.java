package jpa.f_trxtemplate;

import core.h2.H2Server;
import core.h2.ScriptRunner;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

@Configuration
public class TrxTemplatePersistenceConfiguration {

    @Bean
    public CommandLineRunner schemaScriptRunner() {
        return new ScriptRunner(dataSource(), h2Server(), new ClassPathResource("schema.sql"));
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
    public JpaTransactionManager transactionManager() {
        var managerFactory = entityManagerFactoryBean(dataSource()).getObject();

        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(managerFactory);
        return jpaTransactionManager;
    }

    @Bean
    public LocalEntityManagerFactoryBean entityManagerFactoryBean(DataSource dataSource) {
        LocalEntityManagerFactoryBean factory = new LocalEntityManagerFactoryBean();
        factory.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        return factory;
    }


    @Bean
    public TransactionTemplate transactionTemplate() {
        return new TransactionTemplate(this.transactionManager());
    }

}
