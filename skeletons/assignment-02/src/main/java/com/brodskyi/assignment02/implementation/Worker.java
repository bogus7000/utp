package com.brodskyi.assignment02.implementation;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Worker extends Employee {
    private final LocalDate _employmentDate;
    private final BigDecimal _bonus;

    public Worker(String firstName, String surname, LocalDate dateOfBirth, BigDecimal salary, Manager manager, LocalDate employmentDate, BigDecimal bonus) {
        super(firstName, surname, dateOfBirth, salary, manager);
        _employmentDate = employmentDate;
        _bonus = bonus;
    }

    public Worker(String firstName,String surname, LocalDate dateOfBirth, BigDecimal salary, LocalDate employmentDate, BigDecimal bonus) {
        super(firstName, surname, dateOfBirth, salary);
        _employmentDate = employmentDate;
        _bonus = bonus;
    }

    public LocalDate getEmploymentDate() {
        return _employmentDate;
    }

    public BigDecimal getBonus() {
        return _bonus;
    }
}