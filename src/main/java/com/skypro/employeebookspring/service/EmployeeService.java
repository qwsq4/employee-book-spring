package com.skypro.employeebookspring.service;

import com.skypro.employeebookspring.model.Employee;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.skypro.employeebookspring.record.EmployeeRequest;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final Map<Integer, Employee> employees = new HashMap<>();

    public Collection<Employee> getAllEmployees() {
        return this.employees.values();
    }

    public Employee addEmployee(EmployeeRequest employeeRequest) throws IllegalArgumentException{
        if (StringUtils.isEmpty(employeeRequest.getFirstName()) || StringUtils.isEmpty(employeeRequest.getLastName())) {
            throw new IllegalArgumentException("Имя работника должно быть указано");
        }

        if (employees.containsValue(employeeRequest)) {
            throw new IllegalArgumentException("Такой работник уже существует");
        }
            Employee employee = new Employee(employeeRequest.getFirstName(),
                    employeeRequest.getLastName(),
                    employeeRequest.getDepartment(),
                    employeeRequest.getSalary());
            this.employees.put(employee.getId(), employee);
            return employee;
    }

    public Map<Integer, Employee> getEmployees() {
        return employees;
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

    public List<Employee> getSalaryHigh() {
        return employees.values()
                .stream()
                .filter(e -> e.getSalary() > getAverageSalary())
                .collect(Collectors.toList());
    }

    Comparator<Employee> salaryComparator = new Comparator<Employee>() {
        @Override
        public int compare(Employee o1, Employee o2) {
            return Integer.compare(o1.getSalary(), o2.getSalary());
        }
    };
}

