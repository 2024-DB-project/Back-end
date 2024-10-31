package com.example.employee_api.dao;

import com.example.employee_api.model.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeDao {
    List<Employee> getAllEmployees();
    Employee getEmployeeBySsn(String employeeSsn);
    List<Employee> getEmployeeByAttr(String searchAttr, String employeeValue);
    boolean deleteEmployeeBySsn(String employeeSsn);
    Employee updateEmployeeBySsn(String employeeSsn, Map<String, Object> employee);
    Employee addEmployee(List<Object> changeValue);
}
