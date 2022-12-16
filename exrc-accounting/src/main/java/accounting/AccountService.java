package accounting;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class AccountService implements BankingApi {

    private final JdbcTemplate jdbcTemplate;

    private final TransactionTemplate trxTemplate;

    private final AccountDao accountDao;

    public AccountService(DataSource dataSource, TransactionTemplate trxTemplate, AccountDao accountDao) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.trxTemplate = trxTemplate;
        this.accountDao = accountDao;
    }

    public void insertAccount(Account account) {
        accountDao.saveAccount(account);
    }

    public void updateAccount(Account account) {
        System.out.printf("About to update account with number '%s'\n", account.getNumber());
        var rowCount = jdbcTemplate.update("UPDATE accounts SET balance = ? WHERE number = ?", account.getBalance(), account.getNumber());
        System.out.printf("Updated %d row(s)\n", rowCount);
    }

    public void deleteAccount(Account account) {
        System.out.printf("About to delete account with number '%s'\n", account.getNumber());
        var rowCount = jdbcTemplate.update("DELETE FROM accounts WHERE number = ?", account.getNumber());
        System.out.printf("Deleted %d row(s)\n", rowCount);
    }

    public Account findAccount(String accountNumber) {
        var accountList = jdbcTemplate.query("SELECT number, balance FROM accounts WHERE number = ?", new AccountRowMapper(), accountNumber);
        if (accountList.size() == 0) {
            throw new MissingAccountException(accountNumber);
        } else {
            return accountList.get(0);
        }
    }

    public List<Account> findAllAccounts() {
        return jdbcTemplate.query("SELECT number, balance FROM accounts", new AccountRowMapper());
    }

    @Override
    public void deposit(String accountNumber, int amount) {
        System.out.printf("Depositing %d € for account '%s'\n", amount, accountNumber);
        Account account = findAccount(accountNumber);

        account.setBalance(account.getBalance() + amount);
        updateAccount(account);
    }

    @Override
    public void withdraw(String accountNumber, int amount) {
        System.out.printf("Withdrawing %d € from account '%s'\n", amount, accountNumber);
        Account account = findAccount(accountNumber);

        if (account.getBalance() < amount) {
            throw new InsufficientFundsException(accountNumber);
        }

        account.setBalance(account.getBalance() - amount);
        updateAccount(account);
    }

    @Override
    public void transfer(String fromAccountNumber, String toAccountNumber, int amount) {
        trxTemplate.executeWithoutResult(trxStatus -> {
            deposit(toAccountNumber, amount);
            withdraw(fromAccountNumber, amount);
        });
    }

    //
    // --- inner classes ---
    //

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
