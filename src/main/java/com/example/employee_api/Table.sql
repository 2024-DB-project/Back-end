create table if not exists department
(
    dname          varchar(45) not null,
    dnumber        int         not null
        primary key,
    mgr_ssn        char(9) null,
    mgr_start_date date        null
);

create table if not exists employee
(
    fname     varchar(45) not null,
    minit     varchar(45) not null,
    lname     varchar(45) not null,
    ssn       char(9) not null
        primary key,
    bdate     date        not null,
    address   varchar(45) not null,
    sex       varchar(5)  not null,
    salary    double      not null,
    super_ssn char(9) null,
    dno       int         not null,
    created   timestamp   not null,
    modified  timestamp   null,
    constraint employee_department_dnumber_fk
        foreign key (dno) references department (dnumber)
);