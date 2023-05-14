package com.brodskyi.assignment08.comparators;


import com.brodskyi.assignment08.implementation.Person;

import java.util.Comparator;

public class BirthdateComparator implements Comparator<Person> {
    @Override
    public int compare(Person person1, Person person2) {
        return person1.get_birthdate().compareTo(person2.get_birthdate());
    }
}
