package jdbc.a_datasource;

import core.h2.H2Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main090a {

    public static void main(String[] args) {
        System.setProperty("spring.datasource.url", "jdbc:h2:tcp://localhost:9092/~/training.spring.2");
        SpringApplication.run(Main090a.class);
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    public H2Server h2Server() {
        return new H2Server().setPort(9092);
    }

}
