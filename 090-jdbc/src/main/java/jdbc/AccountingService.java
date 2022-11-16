package jdbc;

public interface AccountingService {

    void insertAccount(String accountNumber, int balance);

    void insertAccountWithFault(String accountNumber, int balance);

    Account getAccount(String accountNumber);
}
