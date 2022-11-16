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
    void deposit(int accountNumber, int amount);

    void withdraw(int accountNumber, int amount);

    void transfer(int fromAccountNumber, int toAccountNumber, int amount);
}

````

Details:

- `deposit()` und `withdraw()` sollen eine Exception werfen, wenn es den Account nicht gibt
- implementieren Sie die `transfer()` so, dass erst ein deposit, dann ein withdraw
  aufgerufen wird

Welches Problem tritt auf, wenn man auf einen Account überweist, den es gar nicht gibt?

## 4) Trx

Legen Sie eine
Ergänzen Sie die Anwendung um Transaktionen, sodass ein Fehler in der
`transfer()` Methode keinen inkonsistenten Zustand hinterlässt