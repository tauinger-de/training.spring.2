package jdbc.v_trxproxyfactorybean;

import jdbc.AccountingService;
import jdbc.JdbcConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.interceptor.MatchAlwaysTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionProxyFactoryBean;

@Configuration
public class JdbcConfiguration090v extends JdbcConfiguration {

    @Override
    @Bean
    public AccountingService accountingService() {
        var factoryBean = new TransactionProxyFactoryBean();
        factoryBean.setTarget(super.accountingService());
        factoryBean.setTransactionManager(transactionManager());
        // this causes all methods to have trx-propagation PROPAGATION_REQUIRED
        factoryBean.setTransactionAttributeSource(new MatchAlwaysTransactionAttributeSource());
        factoryBean.afterPropertiesSet();
        return (AccountingService) factoryBean.getObject();
    }
}
