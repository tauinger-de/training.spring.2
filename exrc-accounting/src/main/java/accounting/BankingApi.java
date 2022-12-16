package accounting;

public interface BankingApi {

    void deposit(String accountNumber, int amount);

    void withdraw(String accountNumber, int amount) throws InsufficientFundsException;

    void transfer(String fromAccountNumber, String toAccountNumber, int amount) throws InsufficientFundsException;

}

