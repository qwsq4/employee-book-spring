package com.skypro.employeebookspring.serviceTest;

import com.skypro.employeebookspring.model.Employee;
import com.skypro.employeebookspring.record.EmployeeRequest;
import com.skypro.employeebookspring.service.EmployeeService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

public class EmployeeServiceTest {
    private EmployeeService employeeService;

    @BeforeEach
    public void setup() {
        this.employeeService = new EmployeeService();
        Stream.of(
                new EmployeeRequest("Работник", "Один", 2, 40000),
                new EmployeeRequest("Работник", "Два", 2, 50343),
                new EmployeeRequest("Работник", "Три", 2, 45300))
                .forEach(employeeService::addEmployee);
    }

    @Test
    public void addEmployeeTest() {
        EmployeeRequest employee = new EmployeeRequest("Работник", "Четыре", 2, 53423);
        Employee actual = employeeService.addEmployee(employee);
        Assertions.assertThat(actual.getFirstName()).isEqualTo("Работник");
        Assertions.assertThat(actual.getLastName()).isEqualTo("Четыре");
        Assertions.assertThat(employeeService.getAllEmployees()).contains(actual);
    }

    @Test
    public void addEmployeeEmptyNameTest() {
        EmployeeRequest employee = new EmployeeRequest("", "", 1, 23232);
        try {
            employeeService.addEmployee(employee);
        } catch (IllegalArgumentException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("Имя работника должно быть указано");
        }
    }

    @Test
    public void addEmployeeAlreadyExistTest() {
        EmployeeRequest employee = new EmployeeRequest("Работник", "Один", 2, 40000);
        try {
            employeeService.addEmployee(employee);
        } catch (IllegalArgumentException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo(("Такой работник уже существует"));
        }
    }

    @Test
    public void addEmployeeNameCapitalizeTest() {
        EmployeeRequest employee = new EmployeeRequest("работник", "четыре", 5, 54000);
        Employee actual = employeeService.addEmployee(employee);
        Assertions.assertThat(actual.getFirstName()).isEqualTo("Работник");
        Assertions.assertThat(actual.getLastName()).isEqualTo("Четыре");
    }

    @Test
    public void addEmployeeCorrectNameTest() {
        EmployeeRequest employee = new EmployeeRequest("Работник1", "5", 3, 45633);
        try {
            employeeService.addEmployee(employee);
        } catch (RuntimeException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo(("400 Bad Request"));
        }
    }

    @Test
    public void getSalarySumTest() {
        Assertions.assertThat(employeeService.getSalarySum()).isEqualTo(135643);
    }

    @Test
    public void getSalaryMinTest() {
        Employee actual = employeeService.getSalaryMinEmployee();
        Assertions.assertThat("Работник").isEqualTo(actual.getFirstName());
        Assertions.assertThat("Один").isEqualTo(actual.getLastName());
    }

    @Test
    public void getSalaryMaxTest() {
        Employee actual = employeeService.getSalaryMaxEmployee();
        Assertions.assertThat(actual.getFirstName()).isEqualTo("Работник");
        Assertions.assertThat(actual.getLastName()).isEqualTo("Два");
    }

    @Test
    public void getSalaryHighTest() {
        List<Employee> employees = employeeService.getSalaryHigh();
        Assertions.assertThat(employees.get(0).getFirstName()).isEqualTo("Работник");
        Assertions.assertThat(employees.get(0).getLastName()).isEqualTo("Два");
    }
}
