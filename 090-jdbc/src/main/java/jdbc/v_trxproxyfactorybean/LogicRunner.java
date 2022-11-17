package jdbc.v_trxproxyfactorybean;

import jdbc.c_jdbctemplate.JdbcTemplateAccountingService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(100)
public class LogicRunner implements CommandLineRunner {

    private final JdbcTemplateAccountingService accountingService;

    public LogicRunner(JdbcTemplateAccountingService accountingService) {
        this.accountingService = accountingService;
    }

    @Override
    public void run(String... args) throws Exception {
        // happy case
        accountingService.insertAccount("090v", 52000);

        // unhappy case
        try {
            accountingService.insertAccountWithFault("error-v", 0);
        } catch (Exception e) {
            System.out.println("Got exception: " + e.getMessage());
        }
    }
}
