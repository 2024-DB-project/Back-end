package com.example.employee_api.dao;

import com.example.employee_api.model.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeDao {
    List<Employee> getAllEmployees(boolean flag);
    Employee getEmployeeBySsn(String employeeSsn);
    List<Employee> getEmployeeByAttr(List<String> searchAttr, List<Object> employeeValue);
    boolean deleteEmployeeBySsn(String employeeSsn, boolean flag);
    Employee updateEmployeeBySsn(String employeeSsn, Map<String, Object> employee);
    Employee addEmployee(List<Object> addingValue);
}
