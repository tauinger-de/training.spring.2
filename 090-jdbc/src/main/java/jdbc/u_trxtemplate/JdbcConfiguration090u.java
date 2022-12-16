package jdbc.u_trxtemplate;

import core.ctx.PrintBeansRunner;
import jdbc.JdbcConfiguration;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.support.TransactionTemplate;

@Configuration
public class JdbcConfiguration090u extends JdbcConfiguration {

    @Bean
    public TransactionTemplate transactionTemplate() {
        return new TransactionTemplate(this.transactionManager());
    }

    @Bean
    @Order(-200)
    public PrintBeansRunner printBeansRunner(ListableBeanFactory listableBeanFactory) {
        return new PrintBeansRunner(listableBeanFactory, "jdbc");
    }
}
