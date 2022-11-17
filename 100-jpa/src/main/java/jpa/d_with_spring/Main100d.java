package jpa.d_with_spring;

import jpa.PersistenceConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(PersistenceConfiguration.class)
public class Main100d {

    public static void main(String[] args) throws InterruptedException {
        // create context
        SpringApplication.run(Main100d.class);

        // wait so we can check database content with tool
        Thread.sleep(60000);
    }

}
