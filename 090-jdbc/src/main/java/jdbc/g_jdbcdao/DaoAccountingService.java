package jdbc.g_jdbcdao;

import jdbc.Account;
import jdbc.AccountingService;

public class DaoAccountingService implements AccountingService {

    private final AccountDao accountDao;

    public DaoAccountingService(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void insertAccount(String accountNumber, int balance) {
        accountDao.insertAccount(accountNumber, balance);
        System.out.println("Added row to accounts");
    }

    public void insertAccountWithFault(String accountNumber, int balance) {
        accountDao.insertAccount(accountNumber, balance);
        throw new RuntimeException("Uh oh, something went wrong!");
    }

    @Override
    public Account getAccount(String accountNumber) {
        return accountDao.findAccount(accountNumber);
    }

}
