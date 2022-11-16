package jdbc.e_rowmapper;

import jdbc.Account;
import jdbc.AccountingService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RowMapperAccountingService implements AccountingService {

    private final DataSource dataSource;

    public RowMapperAccountingService(DataSource dataSource) {
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

    @Override
    public Account getAccount(String accountNumber) {
        String sql = "SELECT number, balance FROM accounts WHERE number = ?";
        return new JdbcTemplate(dataSource).queryForObject(
                sql,
                new AccountRowMapper(),
                accountNumber
        );
    }

    private static class AccountRowMapper implements RowMapper<Account> {
        @Override
        public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
            final Account a = new Account();
            a.setNumber(rs.getString(1));
            a.setBalance(rs.getInt(2));
            return a;
        }
    }

}
