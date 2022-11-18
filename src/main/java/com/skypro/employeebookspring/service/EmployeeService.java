package com.skypro.employeebookspring.service;

import com.skypro.employeebookspring.model.Employee;
import org.springframework.stereotype.Service;
import com.skypro.employeebookspring.record.EmployeeRequest;

import java.util.*;

@Service
public class EmployeeService {
    private final Map<Integer, Employee> employees = new HashMap<>();

    public Collection<Employee> getAllEmployees() {
        return this.employees.values();
    }

    public Employee addEmployee(EmployeeRequest employeeRequest) {
        if (employeeRequest.getFirstName() == null || employeeRequest.getLastName() == null) {
            throw new IllegalArgumentException("Имя работника должно быть указано");
        }

        Employee employee = new Employee(employeeRequest.getFirstName(),
                employeeRequest.getLastName(),
                employeeRequest.getDepartment(),
                employeeRequest.getSalary());

        this.employees.put(employee.getId(), employee);
        return employee;
    }

    public int getSalarySum() {
        return employees.values()
                .stream()
                .mapToInt(Employee::getSalary)
                .sum();
    }

    public Employee getSalaryMinEmployee() {
        return employees.values()
                .stream()
                .min(salaryComparator)
                .get();
    }

    public Employee getSalaryMaxEmployee() {
        return employees.values()
                .stream()
                .max(salaryComparator)
                .get();
    }

    public double getAverageSalary() {
        return employees.values()
                .stream()
                .mapToInt(Employee::getSalary)
                .average()
                .getAsDouble();
    }

    public Collection<Employee> getSalaryHigh() {
        Map<Integer, Employee> highSalaryEmployees = new HashMap<>();
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getSalary() > getAverageSalary()) {
                highSalaryEmployees.put(employees.get(i).getId(), employees.get(i));
            }
        }
        return highSalaryEmployees.values();
    }

    Comparator<Employee> salaryComparator = new Comparator<Employee>() {
        @Override
        public int compare(Employee o1, Employee o2) {
            return Integer.compare(o1.getSalary(), o2.getSalary());
        }
    };
}

