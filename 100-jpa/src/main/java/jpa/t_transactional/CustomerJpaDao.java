package jpa.t_transactional;

import jpa.Customer;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class CustomerJpaDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(propagation = Propagation.REQUIRED)
    public Customer createCustomer(Customer customer) {
        entityManager.persist(customer);
        return customer;
    }
}
