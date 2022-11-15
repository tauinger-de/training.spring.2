package core.ctx;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoreCtxConfiguration {

    @Bean
    public PrintBeansRunner printBeansRunner(ListableBeanFactory listableBeanFactory) {
        return new PrintBeansRunner(listableBeanFactory);
    }

}
