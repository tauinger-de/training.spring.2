package jdbc.u_trx_template;

import jdbc.JdbcConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.support.TransactionTemplate;

@Configuration
public class JdbcConfiguration090u extends JdbcConfiguration {

    @Bean
    public TransactionTemplate transactionTemplate() {
        return new TransactionTemplate(this.transactionManager());
    }

}
