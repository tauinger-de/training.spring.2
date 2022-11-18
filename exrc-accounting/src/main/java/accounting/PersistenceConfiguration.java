package accounting;

import core.h2.H2Server;
import core.h2.ScriptRunner;
import core.jdbc.ConnectionTestRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class PersistenceConfiguration {

    @Bean(initMethod = "start", destroyMethod = "stop")
    public H2Server h2Server(@Value("${h2.port}") int port) {
        return new H2Server().setPort(port);
    }

    @Bean
    public DataSource dataSource(@Value("${h2.url}") String connectionUrl) {
        return new DriverManagerDataSource(connectionUrl);
    }

    @Bean
    public CommandLineRunner connectionTestRunner(DataSource dataSource) {
        return new ConnectionTestRunner(dataSource);
    }

    @Bean
    public CommandLineRunner scriptRunner(DataSource dataSource, H2Server h2Server) {
        return new ScriptRunner(dataSource, h2Server, new ClassPathResource("create.sql"));
    }

}
