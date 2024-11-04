package com.example.employee_api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    private String Dname;
    private int Dnumber;
    private String Mgr_ssn;
    private Date Mgr_start_date;
}