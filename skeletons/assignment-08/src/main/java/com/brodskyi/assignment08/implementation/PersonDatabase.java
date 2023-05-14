package com.brodskyi.assignment08.implementation;

import com.brodskyi.assignment08.comparators.BirthdateComparator;
import com.brodskyi.assignment08.comparators.FirstNameComparator;
import com.brodskyi.assignment08.exceptions.Assignment08Exception;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.*;

public class PersonDatabase {
    private final List<Person> _people;

    public PersonDatabase(List<Person> people) {
        _people = people;
    }

    public void serialize(DataOutputStream output) throws Assignment08Exception {
        try {
            output.writeInt(_people.size());
            for (Person person : _people) {
                person.seralize(output);
            }
        } catch (IOException e) {
            throw new Assignment08Exception(e.getMessage(), e.getCause());
        }
    }

    public static PersonDatabase deserialize(DataInputStream input) throws Assignment08Exception {
        try {
            int size = input.readInt();
            List<Person> people = new ArrayList<>(List.of());
            for (int i = 0; i < size; i++) {
                Person person = Person.deseralize(input);
                people.add(person);
            }
            return new PersonDatabase(people);
        } catch (IOException e) {
            throw new Assignment08Exception(e.getMessage(), e.getCause());
        }
    }

    public List<Person> sortedByFirstName() {
        List<Person> list = new ArrayList<>(_people);
        Comparator<Person> comparator = new FirstNameComparator();
        list.sort(comparator);
        return list;
    }

    public List<Person> sortedBySurnameFirstNameAndBirthdate() {
        List<Person> list = new ArrayList<>(_people);
        list.sort(null);
        return list;
    }

    public List<Person> sortedByBirthdate() {
        List<Person> list = new ArrayList<>(_people);
        Comparator<Person> comparator = new BirthdateComparator();
        list.sort(comparator);
        return list;
    }

    public List<Person> bornOnDay(Date date) {
        return null;
    }

}
