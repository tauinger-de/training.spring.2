package jpa.f_trxtemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main100f {

    public static void main(String[] args) throws InterruptedException {
        // create context
        SpringApplication.run(Main100f.class);

        // wait so we can check database content with tool
        Thread.sleep(60000);
    }

}
