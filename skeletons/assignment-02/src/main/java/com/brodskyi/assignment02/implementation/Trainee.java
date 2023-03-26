package com.brodskyi.assignment02.implementation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

public class Trainee extends Employee {
    private final LocalDate _apprenticeShipStartDate;


    public Trainee(String firstName, String lastName, LocalDate dateOfBirth, BigDecimal salary, Manager manager, LocalDate apprenticeShipStartDate) {
        //
        super(firstName, lastName, dateOfBirth, salary, manager);
        _apprenticeShipStartDate = apprenticeShipStartDate;
    }

    public LocalDate getApprenticeShipStartDate() {
        return _apprenticeShipStartDate;
    }

    public short getApprenticeShipLengthDays() {
        return (short) Period.between(_apprenticeShipStartDate, LocalDate.now()).getDays();
    }
}