package jdbc.a_datasource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class LogicRunner implements CommandLineRunner {

    private final DataSource dataSource;

    public LogicRunner(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void run(String... args) throws Exception {
        var connection = this.dataSource.getConnection();
        System.out.println(connection);
    }
}
