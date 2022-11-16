package jdbc.w_annotations;

import jdbc.AccountingService;
import jdbc.JdbcConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.support.TransactionTemplate;

@Configuration
public class JdbcConfiguration090w extends JdbcConfiguration {

    @Bean
    public TransactionTemplate transactionTemplate() {
        return new TransactionTemplate(this.transactionManager());
    }

    @Override
    @Bean
    public AccountingService accountingService() {
        return new TrandactionalAccountingService(dataSource());
    }
}
