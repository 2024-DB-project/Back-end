package com.example.employee_api.dao;

import com.example.employee_api.model.Department;
import com.example.employee_api.service.DepartmentService;

import java.util.List;
import java.util.Map;

public interface DepartmentDao {
    Department getDepartmentByDnumber(int dnumber);
    List<Department> getDepartmentByAttr(List<String> searchAttr, List<Object> departmentValue);
    boolean deleteDepartmentByDnumber(int dnumber);
    Department updateDepartmentByDnumber(int dnumber, Map<String, Object> updateValue);
    Department addDepartment(List<Object> addingValue);
    Double getDepartmentInfo(int dnumber, DepartmentService.OperationType operationType);
}
