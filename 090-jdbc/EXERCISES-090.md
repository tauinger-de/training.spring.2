# Übungen

Basis der Übung ist das Modul `exrc-accounting`.


## 1) Springify

Stellen Sie die bestehende Anwendung um auf Spring Beans.


## 2) Jdbc Support

Schreiben Sie die Datenbank-Zugriffe um auf eine von Spring unterstützte Technologie.


## 3) Neue Logik

Lassen Sie Ihre Anwendung folgendes Interface implementieren:

````java
public interface BankingApi {
    void deposit(String accountNumber, int amount) throws MissingAccountException;

    void withdraw(String accountNumber, int amount) throws MissingAccountException, InsufficientFundsException;

    void transfer(String fromAccountNumber, String toAccountNumber, int amount) throws MissingAccountException, InsufficientFundsException;
}

````

Implementieren Sie die `transfer()` so, dass erst ein deposit, dann ein withdraw
aufgerufen wird.

Führen Sie eine Überweisung durch.

Welches Problem tritt auf, wenn man mehr Geld überweist, als der zahlende Account aufweist?


## 4) Trx

Ergänzen Sie die Anwendung um Transaktionen, sodass ein Fehler in der
`transfer()` Methode keinen inkonsistenten Zustand hinterlässt.