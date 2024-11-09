package com.example.employee_api.dao;

import com.example.employee_api.model.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeDao {
    Employee getEmployeeBySsn(String employeeSsn);
    List<Employee> getEmployeeByAttr(List<String> searchAttr, List<Object> employeeValue);
    boolean deleteEmployeeBySsn(String employeeSsn);
    Employee updateEmployeeBySsn(String employeeSsn, Map<String, Object> employee);
    Employee restoreEmployeeBySsn(String employeeSsn);
    Employee addEmployee(List<Object> addingValue);
}
