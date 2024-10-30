package com.example.employee_api.service;

import com.example.employee_api.dao.EmployeeDao;
import com.example.employee_api.dao.EmployeeDaoImpl;
import com.example.employee_api.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeDao employeeDao;

    @Autowired
    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }

    @Override
    public Employee getEmployeeBySsn(String employeeSsn) {
        return employeeDao.getEmployeeBySsn(employeeSsn);
    }

    @Override
    public boolean deleteEmployeeBySsn(String employeeSsn) {
        return employeeDao.deleteEmployeeBySsn(employeeSsn);
    }

    @Override
    public Employee updateEmployeeBySsn(String employeeSsn, Employee employee) {
        return employeeDao.updateEmployeeBySsn(employeeSsn, employee);
    }

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeDao.addEmployee(employee);
    }
}
