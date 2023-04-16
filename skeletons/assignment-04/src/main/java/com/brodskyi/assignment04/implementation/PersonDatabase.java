package com.brodskyi.assignment04.implementation;

import com.brodskyi.assignment04.implementation.comparators.BirthdateComparator;
import com.brodskyi.assignment04.implementation.comparators.FirstNameComparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class PersonDatabase {
    private final List<Person> _people;

    public PersonDatabase(List<Person> people) {
        _people = people;
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
