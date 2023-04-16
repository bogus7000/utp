package com.brodskyi.assignment04.implementation;

import java.util.Date;

public final class Person implements Comparable<Person> {
    private final String _firstName;
    private final String _surname;
    private final Date _birthdate;

    public Person(String firstName, String surname, Date birthdate) {
        _firstName = firstName;
        _surname = surname;
        _birthdate = birthdate;
    }

    public String get_firstName() {
        return _firstName;
    }

    public String get_surname() {
        return _surname;
    }

    public Date get_birthdate() {
        return _birthdate;
    }

    @Override
    public int compareTo(Person otherPerson) {
        int result = otherPerson._surname.compareToIgnoreCase(_surname);
        if (result == 0) {
            result = otherPerson._firstName.compareToIgnoreCase(_firstName);
            if (result == 0) {
                result = otherPerson._birthdate.compareTo(_birthdate);
                if (result == 0) {
                    return 0;
                }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return _firstName + " " + _surname + " " + _birthdate;
    }
}
