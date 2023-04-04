package com.brodskyi.assignment03.implementation;

import java.time.LocalDate;
import java.time.Period;

public abstract class Person {
    private final String _firstName;
    private final String _surname;
    private final LocalDate _dateOfBirth;
    protected Person(String firstName, String surname, LocalDate dateOfBirth) {
        _firstName = firstName;
        _surname = surname;
        _dateOfBirth = dateOfBirth;
    }

    public String getFirstName() {
        return _firstName;
    }

    public String getSurname() {
        return _surname;
    }

    public LocalDate getDateOfBirth() {
        return _dateOfBirth;
    }

    public short getAge() {
        return Short.valueOf((short) Period.between(_dateOfBirth, LocalDate.now()).getYears());
    }

    public Boolean isOlderThan(Person p) {
        return _dateOfBirth.isBefore(p.getDateOfBirth());
    }

    public Boolean isOlderThan(short years) {
        return Short.valueOf((short) Period.between(_dateOfBirth, LocalDate.now()).getYears()) > years;
    }

    public Boolean isYoungerThan(Person p) {
        return _dateOfBirth.isBefore(p._dateOfBirth);
    }

    public int compareTo(Person p) {
        return _dateOfBirth.compareTo(p._dateOfBirth);
    }
}