package accounting;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class LogicRunner implements CommandLineRunner {

    private final AccountService accountService;

    public LogicRunner(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void run(String... args) throws SQLException {
        // create two accounts
        accountService.insertAccount(new Account("4711"));
        accountService.insertAccount(new Account("4712"));

        // set balance on #1
        final Account account1 = accountService.findAccount("4711");
        account1.setBalance(account1.getBalance() + 50);
        accountService.updateAccount(account1);

        // set balance on #2
        final Account account2 = accountService.findAccount("4712");
        account2.setBalance(account2.getBalance() + 6000);
        accountService.updateAccount(account2);

        // print each account
        for (final Account account : accountService.findAllAccounts()) {
            System.out.println(account);
        }

        // delete account #1
        accountService.deleteAccount(account1);
    }
}
