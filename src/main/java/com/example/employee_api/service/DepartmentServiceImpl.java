package com.example.employee_api.service;

import com.example.employee_api.dao.DepartmentDaoImpl;
import com.example.employee_api.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.example.employee_api.service.DepartmentService.OperationType.*;

@Service
public class DepartmentServiceImpl implements DepartmentService{
    
    private final DepartmentDaoImpl departmentDao;

    @Autowired
    public DepartmentServiceImpl(DepartmentDaoImpl departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentDao.getAllDepartments(false);
    }

    @Override
    public List<Department> getAllTrashes() {
        return departmentDao.getAllDepartments(true);
    }

    @Override
    public Department getDepartmentByDnumber(int dnumber) {
        return departmentDao.getDepartmentByDnumber(dnumber);
    }

    @Override
    public List<Department> getDepartmentByAttr(List<String> searchAttr, List<Object> departmentValue) {
        return departmentDao.getDepartmentByAttr(searchAttr, departmentValue);
    }

    @Override
    public boolean soft_deleteDepartmentByDnumber(int dnumber) {
        return departmentDao.deleteDepartmentByDnumber(dnumber, false);
    }

    @Override
    public boolean hard_deleteDepartmentByDnumber(int dnumber) {
        return departmentDao.deleteDepartmentByDnumber(dnumber, true);
    }

    @Override
    public Department updateDepartmentByDnumber(int dnumber, Map<String, Object> updateValue) {
        return departmentDao.updateDepartmentByDnumber(dnumber,updateValue);
    }
    @Override
    public Department addDepartment(List<Object> addingValue) {
        return departmentDao.addDepartment(addingValue);
    }
    @Override
    public List<Double> getDepartmentinfo(int dnumber) {
        List<Double> result = new ArrayList<>();
        result.add(departmentDao.getDepartmentInfo(dnumber, AVG));
        result.add(departmentDao.getDepartmentInfo(dnumber, MAX));
        result.add(departmentDao.getDepartmentInfo(dnumber, MIN));
        return result;
    }
}
