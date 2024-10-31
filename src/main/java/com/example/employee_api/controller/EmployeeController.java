package com.example.employee_api.controller;

import com.example.employee_api.model.Employee;
import com.example.employee_api.service.EmployeeService;
import com.example.employee_api.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Employee>> getEmployeeByAttr(
            @RequestParam(value = "search_attr") String searchAttr,
            @RequestParam(value = "employee_value") String employeeValue) {
        List<Employee> employees = employeeService.getEmployeeByAttr(searchAttr, employeeValue);
        return employees != null ? ResponseEntity.ok(employees) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{employee_ssn}")
    public ResponseEntity<String> deleteEmployeeBySsn(@PathVariable("employee_ssn") String employeeSsn) {
        boolean isDeleted = employeeService.deleteEmployeeBySsn(employeeSsn);
        return isDeleted ? ResponseEntity.ok("Employee deleted successfully") : ResponseEntity.notFound().build();
    }

    @PutMapping("/{employee_ssn}")
    public ResponseEntity<Employee> updateEmployeeBySsn(
            @PathVariable("employee_ssn") String employeeSsn,
            @RequestBody Employee employee) {
        Employee updatedEmployee = employeeService.updateEmployeeBySsn(employeeSsn, employee);
        return updatedEmployee != null ? ResponseEntity.ok(updatedEmployee) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        Employee createdEmployee = employeeService.addEmployee(employee);
        return ResponseEntity.status(201).body(createdEmployee);
    }
}