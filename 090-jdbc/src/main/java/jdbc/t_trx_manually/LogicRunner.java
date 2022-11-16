package jdbc.t_trx_manually;

import jdbc.c_jdbctemplate.JdbcTemplateAccountingService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Component
@Order(100)
public class LogicRunner implements CommandLineRunner {

    private final PlatformTransactionManager trxMgr;
    private final JdbcTemplateAccountingService accountingService;

    public LogicRunner(PlatformTransactionManager trxMgr, JdbcTemplateAccountingService accountingService) {
        this.trxMgr = trxMgr;
        this.accountingService = accountingService;
    }

    @Override
    public void run(String... args) throws Exception {
        // setup trx
        final DefaultTransactionDefinition trxDef = new DefaultTransactionDefinition();
        trxDef.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        final TransactionStatus trxStatus = trxMgr.getTransaction(trxDef);

        //
        try {
            accountingService.insertAccount("090u", 100);
            trxMgr.commit(trxStatus);
        } catch (Throwable t) {
            // rollback in case of problem
            trxMgr.rollback(trxStatus);
            throw t;
        }
    }
}
