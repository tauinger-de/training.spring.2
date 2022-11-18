package accounting;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

@SpringBootTest
class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Test
    void databaseInteraction() throws SQLException {
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
        for (final Account account : accountService.findAllAccounts())
            System.out.println(account);

        // delete account #1
        accountService.deleteAccount(account1);
    }

    @Test
    void insertAndFindAccount() throws SQLException {
        // given
        var accountNumber = "123-456-789";

        // when
        accountService.insertAccount(new Account(accountNumber));
        var retrievedAccount = accountService.findAccount(accountNumber);

        // then
        Assertions.assertThat(retrievedAccount).isNotNull();  // assertj FTW !!!!
        Assertions.assertThat(retrievedAccount.getNumber()).isEqualTo(accountNumber);
    }

    @Test
    void concurrentFindAccount() throws SQLException {
        // given
        var accountNumber = "444-333-222";
        accountService.insertAccount(new Account(accountNumber));

        // when
        Runnable findRunner = () -> {
            for (int n=0; n<100; n++) {
                accountService.findAccount(accountNumber);
            }
        };
        new Thread(findRunner).start();
        new Thread(findRunner).start();
    }
}