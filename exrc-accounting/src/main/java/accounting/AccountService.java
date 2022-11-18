package accounting;

import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    private final Connection connection;

    public AccountService(Connection connection) {
        this.connection = connection;
    }

    public void insertAccount(Account account) throws SQLException {
        final String sql = "insert into accounts (number, balance) values (?, ?)";
        try (final PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, account.getNumber());
            ps.setInt(2, account.getBalance());
            if (ps.executeUpdate() != 1)
                throw new RuntimeException("account not inserted: " + account.getNumber());
        }
    }

    public void updateAccount(Account account) throws SQLException {
        final String sql = "update accounts set balance = ? where number = ?";
        try (final PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, account.getBalance());
            ps.setString(2, account.getNumber());
            if (ps.executeUpdate() != 1)
                throw new RuntimeException("account not updated: " + account.getNumber());
        }
    }

    public void deleteAccount(Account account) throws SQLException {
        final String sql = "delete from accounts where number = ?";
        try (final PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, account.getNumber());
            if (ps.executeUpdate() != 1)
                throw new RuntimeException("account not deleted: " + account.getNumber());
        }
    }

    public Account findAccount(String number) {
        final String sql = "select number, balance from accounts where number = ?";
        try (final PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, number);
            try (final ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return null;
                }
                final Account a = new Account();
                a.setNumber(rs.getString(1));
                a.setBalance(rs.getInt(2));
                return a;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Account> findAllAccounts() throws SQLException {
        final String sql = "select number, balance from accounts";
        try (final PreparedStatement ps = connection.prepareStatement(sql)) {
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
