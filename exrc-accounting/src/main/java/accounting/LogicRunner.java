package accounting;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class LogicRunner implements CommandLineRunner {

    private final AccountService accountService;

    public LogicRunner(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void run(String... args) {
        // create two accounts
        accountService.insertAccount(new Account("4711"));
        accountService.insertAccount(new Account("4712"));

        // set balance on #1
        final Account account1 = accountService.findAccount("4711");
        account1.setBalance(account1.getBalance() + 50);
        accountService.updateAccount(account1);

        // transfer
        try {
            accountService.transfer("4711", "4712", 60);
        } catch (InsufficientFundsException e) {
            System.out.printf("Transfer FAILED: %s\n", e.getMessage());
        }

        // print each account
        for (final Account account : accountService.findAllAccounts()) {
            System.out.println(account);
        }

        // delete account #1
        accountService.deleteAccount(account1);
    }
}
