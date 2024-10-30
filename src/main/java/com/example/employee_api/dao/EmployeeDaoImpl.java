package com.example.employee_api.dao;

import com.example.employee_api.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    private final DBManager dbManager;

    @Autowired
    public EmployeeDaoImpl(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM EMPLOYEE";

        try {
            List<Map<String, Object>> results = dbManager.executeQuery(query);

            for (Map<String, Object> row : results) {
                Employee employee = new Employee();
                employee.setFname((String) row.get("Fname"));
                employee.setMinit((String) row.get("Minit"));
                employee.setLname((String) row.get("Lname"));
                employee.setSsn((String) row.get("Ssn"));
                employee.setBdate((Date) row.get("Bdate"));
                employee.setAddress((String) row.get("Address"));
                employee.setSex((String) row.get("Sex"));
                employee.setSalary(((BigDecimal) row.get("Salary")).doubleValue());
                employee.setSuperSsn((String) row.get("Super_ssn"));
                employee.setDno((int) row.get("Dno"));
                employee.setCreated((Timestamp) row.get("created"));
                employee.setModified((Timestamp) row.get("modified"));

                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

    @Override
    public Employee getEmployeeBySsn(String employeeSsn) {
        Employee employee = null;
        // 세부 구현 필요
        return employee;
    }

    @Override
    public boolean deleteEmployeeBySsn(String employeeSsn) {
        // 세부 구현 필요
        return false;
    }

    @Override
    public Employee updateEmployeeBySsn(String employeeSsn, Employee employee) {
        // 세부 구현 필요
        return employee;
    }

    @Override
    public Employee addEmployee(Employee employee) {
        // 세부 구현 필요
        return employee;
    }
}
