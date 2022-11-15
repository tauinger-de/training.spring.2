package jdbc.a_datasource;

import core.ctx.CoreCtxConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(CoreCtxConfiguration.class)
public class Main090a {

    public static void main(String[] args) {
        SpringApplication.run(Main090a.class);
    }

}
