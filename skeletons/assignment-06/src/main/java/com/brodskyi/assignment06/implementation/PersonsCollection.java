package com.brodskyi.assignment06.implementation;

import com.brodskyi.assignment06.api.CustomCollection;
import com.brodskyi.assignment06.api.Person;
import com.brodskyi.assignment06.model.Nationality;

import java.text.Collator;
import java.util.*;
import java.util.stream.Collectors;

public class PersonsCollection<T extends Person> extends CustomCollection<T> {
    public List<T> filterAndSortByNationality(Nationality nationality) {
        List<T> results = this.getAll().stream()
                .filter(person -> person.getNationality() == nationality)
                .sorted(Comparator.comparing(Person::getFirstName, Collator.getInstance(Nationality.getLocale(nationality))))
                .collect(Collectors.toList());
        Locale locale = Nationality.getLocale(nationality);
        Collections.sort(results, Collator.getInstance(locale));
        return results;
    }

    @Override
    public String toString() {
        return "PersonsCollection{}";
    }
}
