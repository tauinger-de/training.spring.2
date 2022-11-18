package core.h2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@Order(0)
public class ScriptRunner implements CommandLineRunner {

    private final DataSource dataSource;
    private final H2Server h2Server;
    private final Resource scriptResource;

    public ScriptRunner(DataSource dataSource, H2Server h2Server, Resource scriptResource) {
        this.dataSource = dataSource;
        this.h2Server = h2Server;
        this.scriptResource = scriptResource;
    }

    @Override
    public void run(String... args) throws Exception {
        h2Server.executeScript(dataSource, scriptResource);
    }
}
