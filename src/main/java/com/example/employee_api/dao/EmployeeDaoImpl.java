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
    public List<Employee> getEmployeeByAttr(String searchAttr, String employeeValue) {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM EMPLOYEE WHERE " + searchAttr + " = ?";

        try {
            List<Map<String, Object>> results = dbManager.executeQuery(query, employeeValue);

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
        // 세부 구현 필요
        return employees;
    }

    @Override
    public boolean deleteEmployeeBySsn(String employeeSsn) {
        String query = "DELETE FROM EMPLOYEE WHERE Ssn = ?";

        try {
            int result = dbManager.executeUpdate(query, employeeSsn);
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Employee updateEmployeeBySsn(String employeeSsn, Employee employee) {
//        String query = "UPDATE EMPLOYEE SET"
        return employee;
    }

    @Override
    public Employee addEmployee(Employee employee) {
        // 세부 구현 필요
        return employee;
    }
}
