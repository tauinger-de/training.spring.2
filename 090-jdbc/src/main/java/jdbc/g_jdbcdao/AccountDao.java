package jdbc.g_jdbcdao;

import jdbc.Account;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDao extends JdbcDaoSupport {

    public void insertAccount(String accountNumber, int balance) {
        getJdbcTemplate().update(
                "INSERT INTO accounts VALUES (?, ?)",
                accountNumber, balance
        );
    }

    public Account findAccount(String accountNumber) {
        String sql = "SELECT number, balance FROM accounts WHERE number = ?";
        return getJdbcTemplate().queryForObject(
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
