package core.jdbc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;

@Component
@Order(-100)
public class ConnectionTestRunner implements CommandLineRunner {

    private final DataSource dataSource;

    public ConnectionTestRunner(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.print("Testing connection... ");
        try {
            var connection = dataSource.getConnection();
            System.out.printf("SUCCESS: \"%s\"\n", connection.getMetaData());
        } catch (SQLException e) {
            System.out.printf("FAILED: \"%s\"\n", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
