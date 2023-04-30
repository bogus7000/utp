package com.brodskyi.assignment06.implementation;

import java.util.List;

public class Department {
    private final String name;
    private final List<Teacher> employees;

    public Department(String name, List<Teacher> employees) {
        this.name = name;
        this.employees = employees;
    }

    public String getName() {
        return name;
    }

    public List<Teacher> getEmployees() {
        return employees;
    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", employees=" + employees +
                '}';
    }
}
