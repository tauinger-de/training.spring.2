DROP TABLE IF EXISTS customers;
drop table if exists HIBERNATE_SEQUENCE;

CREATE TABLE customers
(
    id int,
    fname varchar2(100),
    dob timestamp,
    PRIMARY KEY (id)
);