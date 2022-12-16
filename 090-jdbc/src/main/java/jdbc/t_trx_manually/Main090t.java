package jdbc.t_trx_manually;

import jdbc.JdbcConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({JdbcConfiguration.class})
public class Main090t {

    public static void main(String[] args) {
        SpringApplication.run(Main090t.class);
    }


}
