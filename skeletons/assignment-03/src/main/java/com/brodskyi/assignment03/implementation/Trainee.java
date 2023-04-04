package com.brodskyi.assignment03.implementation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

public class Trainee extends Employee {
    private final LocalDate _apprenticeshipStartDate;

    public Trainee(String firstName, String lastName, LocalDate dateOfBirth, BigDecimal salary, Manager manager, LocalDate apprenticeShipStartDate) {
        //
        super(firstName, lastName, dateOfBirth, salary, manager);
        _apprenticeshipStartDate = apprenticeShipStartDate;
    }

    public LocalDate getApprenticeshipStartDate() {
        return _apprenticeshipStartDate;
    }

    public short getApprenticeshipDays() {
        return (short) Period.between(_apprenticeshipStartDate, LocalDate.now()).getDays();
    }

    public Boolean isApprenticeshipDaysShorterThan(short days) {
        short appDays = this.getApprenticeshipDays();
        return appDays > days;
    }

    public Boolean isApprenticeshipDaysLongerThan(short days) {
        short appDays = this.getApprenticeshipDays();
        return appDays < days;
    }

}