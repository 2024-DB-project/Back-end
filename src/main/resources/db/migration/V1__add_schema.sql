CREATE TABLE IF NOT EXISTS EMPLOYEE(
  Fname VARCHAR(15) NOT NULL,
  Minit CHAR,
  Lname VARCHAR(15) NOT NULL,
  Ssn   CHAR(9)     NOT NULL,
  Bdate DATE,
  Address VARCHAR(30),
  Sex   CHAR,
  Salary DECIMAL(10,2),
  Super_ssn CHAR(9),
  Dno INT NOT NULL DEFAULT 1,
  PRIMARY KEY (Ssn),
  created TIMESTAMP,
  modified TIMESTAMP
);

CREATE TABLE IF NOT EXISTS DEPARTMENT(
  Dname VARCHAR(15) NOT NULL,
  Dnumber INT NOT NULL,
  Mgr_ssn CHAR(9) NOT NULL default '888665555',
  Mgr_start_date DATE,
  PRIMARY KEY (Dnumber),
  UNIQUE (Dname)
);

create table IF NOT EXISTS DEPT_LOCATIONS(
  Dnumber int not null,
  Dlocation varchar(15) not null,
  primary key (Dnumber, Dlocation)
);

create table IF NOT EXISTS PROJECT(
  Pname VARCHAR(15) NOT NULL,
  Pnumber INT not null,
  Plocation varchar(15),
  Dnum int not null,
  primary key(Pnumber)
);

CREATE TABLE IF NOT EXISTS WORKS_ON(
  Essn char(9) not null,
  Pno int NOT NULL,
  Hours decimal(3,1),
  primary key(Essn, Pno)
);

create table IF NOT EXISTS DEPENDENT(
  Essn char(9) not null,
  Dependent_name VARCHAR(15) not null,
  Sex char,
  Bdate DATE,
  Relationship varchar(8),
  primary key(Essn, Dependent_name)
);