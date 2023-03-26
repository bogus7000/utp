package com.brodskyi.assignment02.implementation;


import java.math.BigDecimal;
import java.time.LocalDate;
public abstract class Employee extends Person {
    private final BigDecimal _salary;
    private Manager _manager = null;

    protected Employee(String firstName, String lastName, LocalDate dateOfBirth, BigDecimal salary, Manager manager) {
        super(firstName, lastName, dateOfBirth);
        _salary = salary;
        _manager = manager;
    }

    protected Employee(String firstName, String lastName, LocalDate dateOfBirth, BigDecimal salary) {
        super(firstName, lastName, dateOfBirth);
        _salary = salary;
    }

    public BigDecimal getSalary() {
        return _salary;
    }

    public Manager getManager() {
        if (_manager != null) {
            return _manager;
        } else {
            return null;
        }
    }
}