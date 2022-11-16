package jdbc;

import core.h2.H2Server;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class JdbcConfiguration {

    @Bean
    public AccountingService accountingService() {
        return new AccountingService(dataSource());
    }

    @Bean
    public CommandLineRunner schemaScriptRunner() {
        return new SchemaScriptRunner(dataSource(), h2Server());
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    public H2Server h2Server() {
        return new H2Server().setPort(9092);
    }

    @Bean
    public DataSource dataSource() {
        return new DriverManagerDataSource("jdbc:h2:tcp://localhost:9092/~/training.spring.2");
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

}