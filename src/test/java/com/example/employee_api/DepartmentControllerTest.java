package com.example.employee_api;

import com.example.employee_api.model.Department;
import com.example.employee_api.model.Employee;
import com.example.employee_api.service.DepartmentServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DepartmentControllerTest {

    @Autowired
    private DepartmentServiceImpl departmentService;

    @Autowired
    PlatformTransactionManager transactionManager;
    TransactionStatus status;

    // Logger 생성
    private static final Logger logger = LoggerFactory.getLogger(DepartmentControllerTest.class);

    @BeforeEach
    void beforeEach() {
        status = transactionManager.getTransaction(new DefaultTransactionDefinition());
    }

    @AfterEach
    void afterEach() {
        transactionManager.rollback(status);
    }

    @Test
    void 부서_추가() {
        String dname = "NEW DEPARTMENT";
        int dnumber = 10;
        String mgr_ssn = null;
        Date mgr_start_date = null;

        int result = departmentService.createDepartment(dname, dnumber, mgr_ssn, mgr_start_date);

        assertThat(result).isEqualTo(1);
    }

    @Test
    void 부서_수정() {
        int result = departmentService.updateDepartment("new", 1, null, null);

        assertThat(result).isEqualTo(1);
    }

    @Test
    void 부서_매니저_추가() {
        int result = departmentService.insertManager(1, "123456789", new Timestamp(new Date().getTime()));

        assertThat(result).isEqualTo(1);
    }

    @Test
    void 부서_번호로_부서_단건_조회() {
        Department departmentByDnumber = departmentService.getDepartmentByDnumber(1);

        logger.info("result: {}", departmentByDnumber);
        assertThat(departmentByDnumber).isNotNull();
    }

    @Test
    void 부서_전체_조회() {
        List<Department> allDepartments = departmentService.getAllDepartments();
        for (Department department : allDepartments) {
            logger.info("department: {}", department);
        }
        assertThat(allDepartments).hasSize(3);
    }

    @Test
    void 부서에_해당하는_사원_조회() {
        List<Employee> employeesByDnumber = departmentService.getEmployeesByDnumber(5);
        for (Employee employee : employeesByDnumber) {
            logger.info("employee: {}", employee);
        }
        assertThat(employeesByDnumber).hasSize(4);
    }

    @Test
    void 부서에_따른_월급_평균() {
        Double averageSalaryByDepartment = departmentService.getAverageSalaryByDepartment(5);
        logger.info("averageSalaryByDepartment: {}", averageSalaryByDepartment);
        assertThat(averageSalaryByDepartment).isEqualTo(33250.0);
    }

    @Test
    void 부서에_따른_월급_최대() {
        Double maxSalaryByDepartment = departmentService.getMaxSalaryByDepartment(5);
        logger.info("maxSalaryByDepartment: {}", maxSalaryByDepartment);
        assertThat(maxSalaryByDepartment).isEqualTo(40000.0);
    }

    @Test
    void 부서에_따른_월급_최소() {
        Double minSalaryByDepartment = departmentService.getMinSalaryByDepartment(5);
        logger.info("minSalaryByDepartment: {}", minSalaryByDepartment);
        assertThat(minSalaryByDepartment).isEqualTo(25000.0);
    }
}
