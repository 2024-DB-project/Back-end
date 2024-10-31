package com.example.employee_api.dao;

import com.example.employee_api.model.Employee;

import java.util.List;

public interface EmployeeDao {
    List<Employee> getAllEmployees();
    List<Employee> getEmployeeByAttr(String searchAttr, String employeeValue);
    boolean deleteEmployeeBySsn(String employeeSsn);
    Employee updateEmployeeBySsn(String employeeSsn, Employee employee);
    Employee addEmployee(Employee employee);
}
