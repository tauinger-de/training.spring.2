package accounting;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    public void insertAccount(Account account) {
        if (accountRepository.existsById(account.getNumber())) {
            throw new IllegalStateException("Account exists");
        }
        accountRepository.save(account);
    }

    public void updateAccount(Account account) {
        accountRepository.save(account);
    }

    public void deleteAccount(Account account) {
        accountRepository.deleteById(account.getNumber());
    }

    public Account findAccount(String number) {
        return accountRepository
                .findById(number)
                .orElseThrow(() -> {
                    throw new IllegalStateException("Account not found");
                });
    }

    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }
}
