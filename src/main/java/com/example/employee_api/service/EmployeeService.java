package com.example.employee_api.service;

import com.example.employee_api.model.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    List<Employee> getAllTrashes();
    Employee getEmployeeBySsn(String employeeSsn);
    List<Employee> getEmployeeByAttr(List<String> searchAttr, List<Object> employeeValue);
    boolean soft_deleteEmployeeBySsn(String employeeSsn);
    boolean hard_deleteEmployeeBySsn(String employeeSsn);
    boolean restoreEmployeeBySsn(String employeeSsn);
    Employee updateEmployeeBySsn(String employeeSsn, Map<String, Object> updateValue);
    Employee addEmployee(List<Object> changeValue);
}
