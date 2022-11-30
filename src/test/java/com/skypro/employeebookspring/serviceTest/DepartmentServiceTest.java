package com.skypro.employeebookspring.serviceTest;

import com.skypro.employeebookspring.model.Employee;
import com.skypro.employeebookspring.service.DepartmentService;
import com.skypro.employeebookspring.service.EmployeeService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    private final List<Employee> employees = List.of(
            new Employee("Работник", "Один", 1, 74352),
            new Employee("Работник", "Два", 3, 50343),
            new Employee("Работник", "Три", 1, 86540),
            new Employee("Работник", "Два", 2, 34124),
            new Employee("Работник", "Три", 2, 54234),
            new Employee("Работник", "Три", 3, 76434));

    @Mock
    EmployeeService employeeService;

    @InjectMocks
    DepartmentService departmentService;

    @BeforeEach
    void setup() {
        Mockito
                .when(employeeService.getAllEmployees())
                .thenReturn(employees);
    }

    @Test
    void getDepartmentEmployeesTest() {
        Collection<Employee> actual = departmentService.getDepartmentEmployees(1);
        Assertions.assertThat(actual)
                .hasSize(2)
                .contains(employees.get(0), employees.get(2));
    }

    @Test
    void getDepartmentSalarySum() {
        int actual = departmentService.getDepartmentSalarySum(2);
        Assertions.assertThat(actual).isEqualTo(88358);
    }

    @Test
    void getDepartmentSalaryMax() {
        int actual = departmentService.getDepartmentSalaryMax(3);
        Assertions.assertThat(actual).isEqualTo(76434);
    }

    @Test
    void getDepartmentSalaryMin() {
        int actual = departmentService.getDepartmentSalaryMin(1);
        Assertions.assertThat(actual).isEqualTo(74352);
    }

    @Test
    void getEmployeesGroupedByDepartment() {
        Map<Integer, List<Employee>> actual = departmentService.getEmployeesGroupedByDepartment();

        Assertions.assertThat(actual)
                .hasSize(3)
                .containsKeys(1,2,3)
                .containsValues(List.of(employees.get(0), employees.get(2)),
                        List.of(employees.get(3), employees.get(4)),
                        List.of(employees.get(1), employees.get(5)));
    }
}
