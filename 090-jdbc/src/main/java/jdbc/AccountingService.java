package jdbc;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class AccountingService {

    private final DataSource dataSource;

    public AccountingService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insertAccount(String accountNumber, int balance) {
        new JdbcTemplate(dataSource).update(
                "INSERT INTO accounts VALUES (?, ?)",
                accountNumber, balance
        );
        System.out.println("Added row to accounts");
    }

    public void insertAccountWithFault(String accountNumber, int balance) {
        new JdbcTemplate(dataSource).update(
                "INSERT INTO accounts VALUES (?, ?)",
                accountNumber, balance
        );
        throw new RuntimeException("Uh oh, something went wrong!");
    }
}
