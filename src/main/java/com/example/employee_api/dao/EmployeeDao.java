package com.example.employee_api.dao;

import com.example.employee_api.model.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeDao {
    List<Employee> getAllEmployees();
    List<Employee> getAllTrashes();
    Employee getEmployeeBySsn(String employeeSsn);
    List<Employee> getEmployeeByAttr(List<String> searchAttr, List<String> employeeValue);
    boolean soft_deleteEmployeeBySsn(String employeeSsn);
    boolean hard_deleteEmployeeBySsn(String employeeSsn);
    Employee updateEmployeeBySsn(String employeeSsn, Map<String, Object> employee);
    Employee addEmployee(List<Object> changeValue);
}
