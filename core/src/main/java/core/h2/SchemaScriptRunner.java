package core.h2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@Order(0)
public class SchemaScriptRunner implements CommandLineRunner {

    private final DataSource dataSource;
    private final H2Server h2Server;

    public SchemaScriptRunner(DataSource dataSource, H2Server h2Server) {
        this.dataSource = dataSource;
        this.h2Server = h2Server;
    }

    @Override
    public void run(String... args) throws Exception {
        h2Server.executeScript(dataSource, new ClassPathResource("/schema.sql"));
    }
}
