DROP TABLE IF EXISTS accounts;

CREATE TABLE accounts (
    number VARCHAR2(20),
    balance INT,
    PRIMARY KEY (number)
);