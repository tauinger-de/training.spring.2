package jpa.t_transactional;

import jpa.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class LogicRunner100t implements CommandLineRunner {

    @Autowired
    CustomerJpaDao customerJpaDao;

    @Override
    public void run(String... args) {
        var persistedCustomer = customerJpaDao.createCustomer(
                new Customer("John Wayne 100t", LocalDate.of(1907, 5, 26))
        );
        System.out.println(persistedCustomer);
    }
}

