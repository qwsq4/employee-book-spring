package com.skypro.employeebookspring.model;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

public class Employee {
    private final String ALLOWED_SYMBOLS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ" +
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static int counter;
    private final int id;
    private final String firstName;
    private final String lastName;
    private final int department;
    private final int salary;

    public Employee(String firstName, String lastName, int department, int salary) {
        if (nameIsValid(firstName)) {
            this.firstName = WordUtils.capitalize(firstName);
        } else throw new RuntimeException("400 Bad Request");
        if (nameIsValid(lastName)) {
            this.lastName = WordUtils.capitalize(lastName);
        } else throw new RuntimeException("400 Bad Request");
        this.department = department;
        this.salary = salary;

        this.id = counter;
        counter++;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getDepartment() {
        return department;
    }

    public int getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", department=" + department +
                ", salary=" + salary +
                '}';
    }

    private boolean nameIsValid(String str) {
        if (StringUtils.isEmpty(str)) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!ALLOWED_SYMBOLS.contains(String.valueOf(str.charAt(i)))) {
                return false;
            }
        }
        return true;
    }
}

