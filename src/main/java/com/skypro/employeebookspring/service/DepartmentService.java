package com.skypro.employeebookspring.service;

import com.skypro.employeebookspring.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Collection<Employee> getDepartmentEmployees(int department) {
        return employeeService.getAllEmployees().
                stream().
                filter(e -> e.getDepartment() == department).
                collect(Collectors.toList());
    }

    public int getDepartmentSalarySum(int department) {
        return getDepartmentEmployees(department).
                stream().
                mapToInt(Employee::getSalary).
                sum();
    }

    public int getDepartmentSalaryMax(int department) {
        return getDepartmentEmployees(department).
                stream().
                mapToInt(Employee::getSalary).
                max().
                getAsInt();
    }

    public int getDepartmentSalaryMin(int department) {
        return getDepartmentEmployees(department)
                .stream()
                .mapToInt(Employee::getSalary)
                .min()
                .getAsInt();
    }

    public Map<Integer, List<Employee>> getEmployeesGroupedByDepartment() {
        return employeeService.getAllEmployees().
                stream().
                collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
