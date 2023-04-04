package com.brodskyi.assignment03.implementation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public final class Manager extends Worker {
    private ArrayList<Employee> _subordinates = null;

    public Manager(
            String firstName, String surname, LocalDate dateOfBirth, BigDecimal salary, LocalDate employmentDate,
            BigDecimal bonus, ArrayList<Employee> subordinates
    ) {
        super(firstName, surname, dateOfBirth, salary, employmentDate, bonus);
        _subordinates = subordinates;
    }

    public Manager(
            String firstName, String surname, LocalDate dateOfBirth, BigDecimal salary, LocalDate employmentDate,
            BigDecimal bonus
    ) {
        super(firstName, surname, dateOfBirth, salary, employmentDate, bonus);
    }

    public ArrayList<Employee> subordinates() {
        return _subordinates;
    }

    public void addSubordinate(Employee newSubordinate) {
        if (_subordinates == null) {
            _subordinates = new ArrayList<>();
            _subordinates.add(newSubordinate);
        } else {
            _subordinates.add(newSubordinate);
        }
    }
}