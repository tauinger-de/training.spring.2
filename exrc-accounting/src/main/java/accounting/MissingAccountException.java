package accounting;

public class MissingAccountException extends RuntimeException {

    public MissingAccountException(String accountNumber) {
        super(String.format("Account '%s' cannot be found", accountNumber));
    }
    
}
