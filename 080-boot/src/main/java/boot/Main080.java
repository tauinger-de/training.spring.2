package boot;

import core.ctx.CoreCtxConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(CoreCtxConfiguration.class)
public class Main080 {

    public static void main(String[] args) {
        var context = SpringApplication.run(Main080.class);

        System.out.printf("bean count = %d", context.getBeanDefinitionCount());
    }

}
