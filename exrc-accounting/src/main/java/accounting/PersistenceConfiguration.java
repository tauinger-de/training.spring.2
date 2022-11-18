package accounting;

import core.jdbc.ConnectionTestRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class PersistenceConfiguration {

    @Bean
    public CommandLineRunner connectionTestRunner(DataSource dataSource) {
        return new ConnectionTestRunner(dataSource);
    }

}
