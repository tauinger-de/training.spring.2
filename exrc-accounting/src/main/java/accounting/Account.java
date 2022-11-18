package accounting;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @Column(name = "accnt_nmbr", length = 20)
    private String number;

    private int balance;

    public Account() {
        this("<unknown>");
    }

    public Account(String number) {
        this.number = number;
        this.balance = 0;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getBalance() {
        return this.balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + " [number: " + this.number + ", balance:" + this.balance + "]";
    }
}
