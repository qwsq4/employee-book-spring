package com.skypro.employeebookspring.controller;

import com.skypro.employeebookspring.model.Employee;
import com.skypro.employeebookspring.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RequestMapping("/department")
@RestController
public class DepartmentController {
    DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{id}/employees")
    public Collection<Employee> getDepartmentEmployees(@PathVariable(value = "id") int id) {
        return departmentService.getDepartmentEmployees(id);
    }

    @GetMapping("/{id}/salary/sum")
    public int getDepartmentSalarySum(@PathVariable(value = "id") int id) {
        return departmentService.getDepartmentSalarySum(id);
    }

    @GetMapping("/{id}/salary/max")
    public int getDepartmentSalaryMax(@PathVariable(value = "id") int id) {
        return departmentService.getDepartmentSalaryMax(id);
    }

    @GetMapping("/{id}/salary/min")
    public int getDepartmentSalaryMin(@PathVariable(value = "id") int id) {
        return departmentService.getDepartmentSalaryMin(id);
    }

    @GetMapping("/department/employees")
    public Map<Integer, List<Employee>> getEmployeesGroupedByDepartment() {
        return departmentService.getEmployeesGroupedByDepartment();
    }
}
