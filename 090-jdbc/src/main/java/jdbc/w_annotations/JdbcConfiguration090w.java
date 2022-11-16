package jdbc.w_annotations;

import jdbc.JdbcConfiguration;
import jdbc.c_jdbctemplate.JdbcTemplateAccountingService;
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
    public JdbcTemplateAccountingService accountingService() {
        return new TrandactionalAccountingService(dataSource());
    }
}
