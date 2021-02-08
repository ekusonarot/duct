drop table if exists ex_table;
create table if not exists ex_table 
(
    id   CHAR(10) UNIQUE,
    name CHAR(10),
    age  int
);