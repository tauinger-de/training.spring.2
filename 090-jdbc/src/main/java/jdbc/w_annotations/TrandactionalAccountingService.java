package jdbc.w_annotations;

import jdbc.AccountingService;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

public class TrandactionalAccountingService extends AccountingService {
    public TrandactionalAccountingService(DataSource dataSource) {
        super(dataSource);
    }

    @Transactional
    @Override
    public void insertAccount(String accountNumber, int balance) {
        super.insertAccount(accountNumber, balance);
    }

    @Override
    @Transactional
    public void insertAccountWithFault(String accountNumber, int balance) {
        super.insertAccountWithFault(accountNumber, balance);
    }
}
