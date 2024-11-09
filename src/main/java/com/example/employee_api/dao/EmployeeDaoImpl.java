package com.example.employee_api.dao;

import com.example.employee_api.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
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
    public Employee getEmployeeBySsn(String employeeSsn) {
        Employee employee = new Employee();
        String query = "SELECT * FROM EMPLOYEE WHERE Ssn = ? AND trash = false";

        try {
            Map<String, Object> result = dbManager.executeQuery(query, employeeSsn).get(0);
            if (result != null) {
                employee.setFname((String) result.get("Fname"));
                employee.setMinit((String) result.get("Minit"));
                employee.setLname((String) result.get("Lname"));
                employee.setSsn((String) result.get("Ssn"));
                employee.setBdate((Date) result.get("Bdate"));
                employee.setAddress((String) result.get("Address"));
                employee.setSex((String) result.get("Sex"));
                employee.setSalary(((BigDecimal) result.get("Salary")).doubleValue());
                employee.setSuperSsn((String) result.get("Super_ssn"));
                employee.setDno((int) result.get("Dno"));
                employee.setCreated((Timestamp) result.get("created"));
                employee.setModified((Timestamp) result.get("modified"));
            } else return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public List<Employee> getEmployeeByAttr(List<String> searchAttr, List<Object> employeeValue) {
        List<Employee> employees = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM EMPLOYEE WHERE trash = false AND ");
        for (int i = 0; i < searchAttr.size(); i++) {
            queryBuilder.append(searchAttr.get(i)).append(" = ?");
            if (i < searchAttr.size() - 1) {
                queryBuilder.append(" AND ");
            }
        }
        String query = queryBuilder.toString();

        try {
            List<Map<String, Object>> results = dbManager.executeQuery(query, employeeValue.toArray());

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
    public boolean deleteEmployeeBySsn(String employeeSsn) {
        String query = "DELETE FROM EMPLOYEE WHERE Ssn = ? AND trash = true";
        try {
            int result = dbManager.executeUpdate(query, employeeSsn);
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Employee updateEmployeeBySsn(String employeeSsn, Map<String, Object> changeValue) {
        StringBuilder query = new StringBuilder("UPDATE EMPLOYEE SET ");
        List<Object> params = new ArrayList<>();

        Timestamp currentTimestamp = Timestamp.valueOf(LocalDateTime.now());
        changeValue = new HashMap<>(changeValue);
        changeValue.put("modified", currentTimestamp);

        for (Map.Entry<String, Object> entry: changeValue.entrySet()) {
            query.append(entry.getKey()).append(" = ?, ");
            params.add(entry.getValue());
        }

        query.setLength(query.length() -2);
        query.append(" WHERE Ssn = ? AND trash = false");
        params.add(employeeSsn);

        try {
            int result = dbManager.executeUpdate(query.toString(), params.toArray());
            if (result > 0) {
                return getEmployeeBySsn(employeeSsn);
            }
            else return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Employee addEmployee(List<Object> addingValue) {
        if (addingValue.size() < 10) {
            throw new IllegalArgumentException("Invalid changeValue list");
        }

        String query = "INSERT INTO EMPLOYEE (Fname, Minit, Lname, Ssn, Bdate, Address, Sex, Salary, Super_ssn, Dno, created, modified, trash) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, false)";

        Timestamp currentTimestamp = Timestamp.valueOf(LocalDateTime.now());
        addingValue.add(currentTimestamp);
        addingValue.add(currentTimestamp);

        try {
            int result = dbManager.executeUpdate(query, addingValue.toArray());
            if (result > 0) {
                return getEmployeeBySsn(addingValue.get(3).toString());
            }
            else return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
