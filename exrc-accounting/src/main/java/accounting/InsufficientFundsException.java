package accounting;

public class InsufficientFundsException extends RuntimeException {

    public InsufficientFundsException(String accountNumber) {
        super(String.format("Account '%s' has insufficient funds to complete transaction", accountNumber));
    }

}
