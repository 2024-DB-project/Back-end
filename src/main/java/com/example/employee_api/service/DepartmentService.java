package com.example.employee_api.service;

import com.example.employee_api.model.Department;
import com.example.employee_api.model.Employee;

import java.util.Date;
import java.util.List;

public interface DepartmentService {
    int createDepartment(String dname, int dnumber, String mgr_ssn, Date mgr_start_date);
    int updateDepartment(String dname,int dnumber, String mgr_ssn, Date mgr_start_date);
    int insertManager(int dnumber, String mgr_ssn, Date mgr_start_date);
    Department getDepartmentByDnumber(int dnumber);
    List<Department> getAllDepartments();
    List<Employee> getEmployeesByDnumber(int dnumber);
    Double getAverageSalaryByDepartment(int dnumber);
    Double getMaxSalaryByDepartment(int dnumber);
    Double getMinSalaryByDepartment(int dnumber);
}
