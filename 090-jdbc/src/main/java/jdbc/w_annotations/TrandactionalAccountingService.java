package jdbc.w_annotations;

import jdbc.c_jdbctemplate.JdbcTemplateAccountingService;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

public class TrandactionalAccountingService extends JdbcTemplateAccountingService {
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
