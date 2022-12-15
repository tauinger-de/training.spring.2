package accounting;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class AccountService {

    private final JdbcTemplate jdbcTemplate;

    public AccountService(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void insertAccount(String accountNumber, int balance) {
        System.out.printf("About to insert account with number '%s' and balance %d\n", accountNumber, balance);
        var rowCount = jdbcTemplate.update(
                "INSERT INTO accounts VALUES (?, ?)",
                accountNumber, balance
        );
        System.out.printf("Added %d row(s)\n", rowCount);
    }

    public void insertAccountWithFault(String accountNumber, int balance) {
        jdbcTemplate.update(
                "INSERT INTO accounts VALUES (?, ?)",
                accountNumber, balance
        );
        throw new RuntimeException("Uh oh, something went wrong!");
    }

    public Account getAccount(String accountNumber) {
        var accountList = jdbcTemplate.query(
                "SELECT number, balance FROM accounts WHERE number = ?",
                new AccountRowMapper(),
                accountNumber
        );
        if (accountList.size() == 0) {
            return null;
        } else {
            return accountList.get(0);
        }
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
