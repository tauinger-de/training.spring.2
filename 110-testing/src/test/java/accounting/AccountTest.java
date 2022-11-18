package accounting;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class AccountTest {

    /**
     * Checks instantiation of an Account instance works by asserting getters
     */
    @Test
    void init() {
        // given
        var accountNumber = "123-456";
        var account = new Account(accountNumber);

        // then
        Assertions.assertThat(account.getNumber()).isEqualTo(accountNumber);
        Assertions.assertThat(account.getBalance()).isEqualTo(0);
    }
}
