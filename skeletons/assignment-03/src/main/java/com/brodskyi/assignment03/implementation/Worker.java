package com.brodskyi.assignment03.implementation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.TemporalUnit;

public class Worker extends Employee {
    private final LocalDate _employmentDate;
    private BigDecimal _bonus;

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

    public void setBonus(BigDecimal newBonus) {
        _bonus = newBonus;
    }

    public Boolean hasBonus() {
        return _bonus != null;
    }

    public Boolean hasBonusGreaterThan(BigDecimal bonus) {
        return _bonus.intValue() > bonus.intValue();
    }

    public Boolean isSeniorityLongerThanYears(short years) {
        short employmentYears = (short) Period.between(_employmentDate, LocalDate.now()).getYears();
        return employmentYears > years;
    }

    public Boolean isSeniorityShorterThanYears(short years) {
        short employmentYears = (short) Period.between(_employmentDate, LocalDate.now()).getYears();
        return employmentYears < years;
    }

    public Boolean isSeniorityLongerThanMonths(short months) {
        short employmentMonths = (short) Period.between(_employmentDate, LocalDate.now()).getMonths();
        return employmentMonths > months;
    }

    public Boolean isSeniorityLongerThanWorker(Worker worker) {
        return _employmentDate.isBefore(worker.getEmploymentDate());
    }
}