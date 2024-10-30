package com.example.employee_api.dao;

import com.example.employee_api.model.Employee;

import java.util.List;

public interface EmployeeDao {
    List<Employee> getAllEmployees();
    Employee getEmployeeBySsn(String employeeSsn);
    boolean deleteEmployeeBySsn(String employeeSsn);
    Employee updateEmployeeBySsn(String employeeSsn, Employee employee);
    Employee addEmployee(Employee employee);
}
