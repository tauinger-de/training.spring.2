DROP TABLE IF EXISTS accounts;

CREATE TABLE accounts
(
    accnt_nmbr VARCHAR2(20),
    balance    INT,
    PRIMARY KEY (accnt_nmbr)
);