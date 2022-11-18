package com.skypro.employeebookspring.controller;

import com.skypro.employeebookspring.model.Employee;
import com.skypro.employeebookspring.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.skypro.employeebookspring.record.EmployeeRequest;

import java.util.Collection;

@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public Collection<Employee> getAllEmployees() {
        return this.employeeService.getAllEmployees();
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        return this.employeeService.addEmployee(employeeRequest);
    }

    @GetMapping("/employee/salary/sum")
    public int getSalarySum() {
        return this.employeeService.getSalarySum();
    }

    @GetMapping("/employee/salary/min")
    public Employee getSalaryMin() {
        return this.employeeService.getSalaryMinEmployee();
    }

    @GetMapping("/employee/salary/max")
    public Employee getSalaryMax() {
        return this.employeeService.getSalaryMaxEmployee();
    }

    @GetMapping("/employee/high-salary")
    public Collection<Employee> getSalaryHigh() {
        return this.employeeService.getSalaryHigh();
    }
}
