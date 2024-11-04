package com.example.employee_api.controller;

import com.example.employee_api.model.Employee;
import com.example.employee_api.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Operation(summary = "모든 직원 조회", description = "모든 직원 정보를 조회합니다.", tags = {"조회(GET)"})
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @Operation(summary = "휴지통 직원 조회", description = "휴지통의 모든 직원 정보를 조회합니다.", tags = {"조회(GET)"})
    @GetMapping("/trash")
    public ResponseEntity<List<Employee>> getAllTrashes() {
        List<Employee> employees = employeeService.getAllTrashes();
        return ResponseEntity.ok(employees);
    }

    @Operation(summary = "특정 ssn 직원 조회", description = "ssn값을 통해 특정 직원을 조회합니다.", tags = {"조회(GET)"})
    @GetMapping("/{employee_ssn}")
    public ResponseEntity<Employee> getEmployeeBySsn(@PathVariable("employee_ssn") String employeeSsn) {
        Employee employee = employeeService.getEmployeeBySsn(employeeSsn);
        return employee != null ? ResponseEntity.ok(employee) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "직원 검색", description = "임의의 특성값을 통해 직원을 조회합니다.", tags = {"조회(GET)"})
    @GetMapping("/search")
    public ResponseEntity<?> getEmployeeByAttr(
            @RequestParam(value = "search_attr") List<String> searchAttr,
            @RequestParam(value = "employee_value") List<String> employeeValue) {

        if (searchAttr.size() != employeeValue.size()) {
            return ResponseEntity.badRequest().body("not matching parameters");
        }
        List<Employee> employees = employeeService.getEmployeeByAttr(searchAttr, employeeValue);
        return employees != null ? ResponseEntity.ok(employees) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "직원 삭제(휴지통)", description = "해당하는 ssn값의 직원 정보를 휴지통으로 삭제합니다.", tags = {"삭제(DELETE)"})
    @DeleteMapping("/{employee_ssn}")
    public ResponseEntity<String> soft_deleteEmployeeBySsn(@PathVariable("employee_ssn") String employeeSsn) {
        boolean isDeleted = employeeService.soft_deleteEmployeeBySsn(employeeSsn);
        return isDeleted ? ResponseEntity.ok("Employee deleted successfully") : ResponseEntity.notFound().build();
    }

    @Operation(summary = "직원 완전 삭제", description = "해당하는 ssn값의 직원 정보를 완전히 삭제합니다.", tags = {"삭제(DELETE)"})
    @DeleteMapping("/hard/{employee_ssn}")
    public ResponseEntity<String> hard_deleteEmployeeBySsn(@PathVariable("employee_ssn") String employeeSsn) {
        boolean isDeleted = employeeService.hard_deleteEmployeeBySsn(employeeSsn);
        return isDeleted ? ResponseEntity.ok("Employee deleted successfully") : ResponseEntity.notFound().build();
    }

    @Operation(summary = "직원 정보 수정", description = "특정 ssn값의 직원 정보를 수정합니다.", tags = {"업데이트(PUT)"})
    @PutMapping("/{employee_ssn}")
    public ResponseEntity<Employee> updateEmployeeBySsn(
            @PathVariable("employee_ssn") String employeeSsn,
            @RequestBody Map<String, Object> changeValue) {
        Employee updatedEmployee = employeeService.updateEmployeeBySsn(employeeSsn, changeValue);
        return updatedEmployee != null ? ResponseEntity.ok(updatedEmployee) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "직원 정보 추가", description = "해당하는 데이터의 직원을 새로 추가합니다.", tags = {"추가(POST)"})
    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody List<Object> changeValue) {
        Employee createdEmployee = employeeService.addEmployee(changeValue);
        return ResponseEntity.status(201).body(createdEmployee);
    }
}