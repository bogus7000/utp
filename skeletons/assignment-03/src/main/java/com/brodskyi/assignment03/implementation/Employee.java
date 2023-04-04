package com.brodskyi.assignment03.implementation;


import java.math.BigDecimal;
import java.time.LocalDate;
public abstract class Employee extends Person {
    private BigDecimal _salary;
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

    public void setSalary(BigDecimal _salary) {
        this._salary = _salary;
    }

    public Manager getManager() {
        if (_manager != null) {
            return _manager;
        } else {
            return null;
        }
    }

    public Boolean isSalaryIsGreaterThan(BigDecimal value) {
            return _salary.compareTo(value) == 1;
    }

    public Boolean isSalaryIsLessThan(BigDecimal value) {
            return _salary.compareTo(value) == -1;
    }
}