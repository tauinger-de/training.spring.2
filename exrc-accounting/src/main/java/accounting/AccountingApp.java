package accounting;

import core.h2.H2Server;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountingApp {

    public static void main(String[] args) throws InterruptedException {
        // launch DB and create datasource
        try (final H2Server h2Server = new H2Server().setPort(9092).start()) {
            final DataSource dataSource = createDataSource(h2Server.getPort());

            // execute script
            h2Server.executeScript(dataSource, new ClassPathResource("create.sql"));

            // get DB connection and call app logic
            try (final Connection con = dataSource.getConnection()) {
                new AccountingApp().demo(con);
            } catch (final Exception e) {
                System.out.println(e);
            }

            // wait so we can query database with external tool
            System.out.println("Waiting for 60 seconds to keep database alive for querying...");
            Thread.sleep(60000);
        }
    }

    private static DataSource createDataSource(int port) {
        return new DriverManagerDataSource("jdbc:h2:tcp://localhost:" + port + "/~/training.spring.exrc-accounting");
    }

    private void demo(final Connection con) throws SQLException {
        // create two accounts
        insertAccount(con, new Account("4711"));
        insertAccount(con, new Account("4712"));

        // set balance on #1
        final Account account1 = findAccount(con, "4711");
        account1.setBalance(account1.getBalance() + 50);
        updateAccount(con, account1);

        // set balance on #2
        final Account account2 = findAccount(con, "4712");
        account2.setBalance(account2.getBalance() + 6000);
        updateAccount(con, account2);

        // print each account
        for (final Account account : findAllAccounts(con))
            System.out.println(account);

        // delete account #1
        deleteAccount(con, account1);
    }

    private static void insertAccount(Connection con, Account account) throws SQLException {
        final String sql = "insert into accounts (number, balance) values (?, ?)";
        try (final PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, account.getNumber());
            ps.setInt(2, account.getBalance());
            if (ps.executeUpdate() != 1)
                throw new RuntimeException("account not inserted: " + account.getNumber());
        }
    }

    private static void updateAccount(Connection con, Account account) throws SQLException {
        final String sql = "update accounts set balance = ? where number = ?";
        try (final PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, account.getBalance());
            ps.setString(2, account.getNumber());
            if (ps.executeUpdate() != 1)
                throw new RuntimeException("account not updated: " + account.getNumber());
        }
    }

    private static void deleteAccount(Connection con, Account account) throws SQLException {
        final String sql = "delete from accounts where number = ?";
        try (final PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, account.getNumber());
            if (ps.executeUpdate() != 1)
                throw new RuntimeException("account not deleted: " + account.getNumber());
        }
    }

    private static Account findAccount(Connection con, String number) throws SQLException {
        final String sql = "select number, balance from accounts where number = ?";
        try (final PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, number);
            try (final ResultSet rs = ps.executeQuery()) {
                if (!rs.next())
                    return null;
                final Account a = new Account();
                a.setNumber(rs.getString(1));
                a.setBalance(rs.getInt(2));
                return a;
            }
        }
    }

    private static List<Account> findAllAccounts(Connection con) throws SQLException {
        final String sql = "select number, balance from accounts";
        try (final PreparedStatement ps = con.prepareStatement(sql)) {
            try (final ResultSet rs = ps.executeQuery()) {
                final List<Account> list = new ArrayList<>();
                while (rs.next()) {
                    final Account a = new Account();
                    a.setNumber(rs.getString(1));
                    a.setBalance(rs.getInt(2));
                    list.add(a);
                }
                return list;
            }
        }
    }

}
