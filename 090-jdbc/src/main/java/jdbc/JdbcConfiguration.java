package jdbc;

import core.h2.H2Server;
import core.h2.ScriptRunner;
import core.jdbc.ConnectionTestRunner;
import jdbc.c_jdbctemplate.JdbcTemplateAccountingService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class JdbcConfiguration {

    @Bean
    public AccountingService accountingService() {
        return new JdbcTemplateAccountingService(dataSource());
    }

    @Bean
    public CommandLineRunner schemaScriptRunner() {
        return new ScriptRunner(dataSource(), h2Server(), new ClassPathResource("schema.sql"));
    }

    @Bean
    public CommandLineRunner connectionTestRunner() {
        return new ConnectionTestRunner(dataSource());
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    public H2Server h2Server() {
        return new H2Server().setPort(9092);
    }

    @Bean
    public DataSource dataSource() {
        return new DriverManagerDataSource("jdbc:h2:tcp://localhost:9092/~/training.spring.090-jdbc");
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

}
