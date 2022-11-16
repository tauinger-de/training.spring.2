package jdbc.c_jdbctemplate;

import jdbc.Account;
import jdbc.AccountingService;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTemplateAccountingService implements AccountingService {

    private final DataSource dataSource;

    public JdbcTemplateAccountingService(DataSource dataSource) {
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
        return new JdbcTemplate(dataSource).query(
                "SELECT number, balance FROM accounts WHERE number = ?",
                new AccountResultSetExtractor(),
                accountNumber
        );
    }


    private static class AccountResultSetExtractor implements ResultSetExtractor<Account> {

        @Override
        public Account extractData(ResultSet rs) throws SQLException, DataAccessException {
            Account account = new Account();
            account.setNumber(rs.getString("number"));
            account.setBalance(rs.getInt("balance"));
            return account;
        }
    }

}
