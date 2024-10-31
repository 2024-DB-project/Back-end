package com.example.employee_api.service;

import com.example.employee_api.dao.EmployeeDao;
import com.example.employee_api.dao.EmployeeDaoImpl;
import com.example.employee_api.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    public Employee getEmployeeBySsn(String employeeSsn) {
        return employeeDao.getEmployeeBySsn(employeeSsn);
    }
    @Override
    public List<Employee> getEmployeeByAttr(String searchAttr, String employeeValue) {
        return employeeDao.getEmployeeByAttr(searchAttr, employeeValue);
    }

    @Override
    public boolean deleteEmployeeBySsn(String employeeSsn) {
        return employeeDao.deleteEmployeeBySsn(employeeSsn);
    }

    @Override
    public Employee updateEmployeeBySsn(String employeeSsn, Map<String, Object> updateValue) {
        return employeeDao.updateEmployeeBySsn(employeeSsn, updateValue);
    }

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeDao.addEmployee(employee);
    }
}
