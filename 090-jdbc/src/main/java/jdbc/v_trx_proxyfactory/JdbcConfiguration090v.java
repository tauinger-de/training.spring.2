package jdbc.v_trx_proxyfactory;

import jdbc.JdbcConfiguration;
import jdbc.c_jdbctemplate.JdbcTemplateAccountingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.interceptor.MatchAlwaysTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionProxyFactoryBean;
import org.springframework.transaction.support.TransactionTemplate;

@Configuration
public class JdbcConfiguration090v extends JdbcConfiguration {

    @Bean
    public TransactionTemplate transactionTemplate() {
        return new TransactionTemplate(this.transactionManager());
    }

    @Override
    @Bean
    public JdbcTemplateAccountingService accountingService() {
        var factoryBean = new TransactionProxyFactoryBean();
        factoryBean.setTarget(super.accountingService());
        factoryBean.setTransactionManager(transactionManager());
        // this causes all methods to have trx-propagation PROPAGATION_REQUIRED
        factoryBean.setTransactionAttributeSource(new MatchAlwaysTransactionAttributeSource());
        factoryBean.afterPropertiesSet();
        return (JdbcTemplateAccountingService) factoryBean.getObject();
    }
}
