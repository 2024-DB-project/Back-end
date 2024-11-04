package com.example.employee_api.service;

import com.example.employee_api.dao.DepartmentDao;
import com.example.employee_api.model.Department;
import com.example.employee_api.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DepartmentServiceImpl {
    
    private final DepartmentDao departmentDao;

    @Autowired
    public DepartmentServiceImpl(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }
    
    // 01. 부서 추가
    public int createDepartment(String dname, int dnumber, String mgr_ssn, Date mgr_start_date) {
        return departmentDao.createDepartment(dname, dnumber, mgr_ssn, mgr_start_date);
    }

    // 02. 부서 수정
    public int updateDepartment(String dname,int dnumber, String mgr_ssn, Date mgr_start_date) {
        return departmentDao.updateDepartment(dname, dnumber, mgr_ssn, mgr_start_date);
    }

    // 03. 매니저 추가
    public int insertManager(int dnumber, String mgr_ssn, Date mgr_start_date) {
        return departmentDao.insertMgrValueToDepartment(dnumber, mgr_ssn, mgr_start_date);
    }

    // 04. 부서 번호로 부서 단건 조회
    public Department getDepartmentByDnumber(int dnumber) {
        return departmentDao.getDepartmentByDnumber(dnumber);
    }

    // 05. 부서 전체 조회
    public List<Department> getAllDepartments() {
        return departmentDao.getAllDepartments();
    }

    // 06. 부서 번호에 해당하는 사원 찾기
    public List<Employee> getEmployeesByDnumber(int dnumber) {
        return departmentDao.getEmployeesByDnumber(dnumber);
    }

    // 07. 부서에 따른 월급 평균
    public Double getAverageSalaryByDepartment(int dnumber) {
        return departmentDao.getAverageSalaryByDepartment(dnumber);
    }

    // 08. 부서에 따른 월급 최대
    public Double getMaxSalaryByDepartment(int dnumber) {
        return departmentDao.getMaxSalaryByDepartment(dnumber);
    }

    // 09. 부서에 따른 월급 최소
    public Double getMinSalaryByDepartment(int dnumber) {
        return departmentDao.getMinSalaryByDepartment(dnumber);
    }
}
