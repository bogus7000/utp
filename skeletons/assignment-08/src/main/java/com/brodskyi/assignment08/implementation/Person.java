package com.brodskyi.assignment08.implementation;

import com.brodskyi.assignment08.exceptions.Assignment08Exception;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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

    public void seralize(DataOutputStream output) throws Assignment08Exception {
        try {
            output.writeUTF(_firstName);
            output.writeUTF(_surname);
            output.writeLong(_birthdate.getTime());
        } catch (IOException e) {
            throw new Assignment08Exception(e.getMessage(), e.getCause());
        }
    }

    public static Person deseralize(DataInputStream input) throws Assignment08Exception {
        try {
            String firstName = input.readUTF();
            String surname = input.readUTF();
            Date birthdate = new Date(input.readLong());
            return new Person(firstName, surname, birthdate);
        } catch (IOException e) {
            throw new Assignment08Exception(e.getMessage(), e.getCause());
        }
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
