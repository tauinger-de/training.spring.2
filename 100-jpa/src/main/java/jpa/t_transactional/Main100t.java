package jpa.t_transactional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main100t {

    public static void main(String[] args) throws InterruptedException {
        // create context
        SpringApplication.run(Main100t.class);

        // wait so we can check database content with tool
        Thread.sleep(60000);
    }

}
