package jdbc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@Order(50)
public class ConnectionTestRunner implements CommandLineRunner {

    private final DataSource dataSource;

    public ConnectionTestRunner(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.printf("Testing connection...\n");
        var connection = dataSource.getConnection();
        System.out.printf("Got connection: %s\n", connection.getMetaData());
    }
}
