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
        // given
        var accountNumber = "88-306-106-00";

        // when
        accountService.insertAccount(accountNumber, 100);
        var account = accountService.getAccount(accountNumber);

        // then
        System.out.println(account);
    }
}
