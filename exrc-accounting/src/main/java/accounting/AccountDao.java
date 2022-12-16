package accounting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class AccountDao {

    private EntityManager entityManager;

    @Autowired
    TransactionTemplate transactionTemplate;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void saveAccount(Account account) {
        transactionTemplate.executeWithoutResult(x -> {
            entityManager.persist(account);
        });
    }
}
