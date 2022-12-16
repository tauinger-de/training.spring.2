package jdbc.u_trxtemplate;

import jdbc.AccountingService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

@Component
@Order(100)
public class LogicRunner implements CommandLineRunner {

    private final TransactionTemplate trxTemplate;
    private final AccountingService accountingService;

    public LogicRunner(TransactionTemplate trxTemplate, AccountingService accountingService) {
        this.trxTemplate = trxTemplate;
        this.accountingService = accountingService;
    }

    @Override
    public void run(String... args) throws Exception {
        //
        trxTemplate.executeWithoutResult(trxStatus -> {
            accountingService.insertAccount("090u", 5000);
        });
    }
}
