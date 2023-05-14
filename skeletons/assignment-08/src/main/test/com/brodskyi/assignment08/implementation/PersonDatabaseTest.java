package com.brodskyi.assignment08.implementation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;

public class PersonDatabaseTest {
    private static final String PATH_PREFIX = "src/main/test/com/brodskyi/assignment04/test/";
    private static final String MOCK_FILE_SORTING = PATH_PREFIX + "persons-operations-sorting.txt";
    private PersonDatabase _db;

    @Before
    public void before() throws FileNotFoundException, ParseException {
        List<Person> _persons = InputParser.parse(MOCK_FILE_SORTING);
        _db = new PersonDatabase(_persons);
    }

    @Test
    public void shouldSortPersonsNaturally() {
        List<Person> sortedNaturally = _db.sortedBySurnameFirstNameAndBirthdate();
        Assert.assertEquals("Smith", sortedNaturally.get(0).get_surname());
    }

    @Test
    public void shouldSortPersonsByFirstName() {
        List<Person> sortedByFirstName = _db.sortedByFirstName();
        Assert.assertEquals("Smith", sortedByFirstName.get(0).get_surname());
    }

    @Test
    public void shouldSortPersonsByBirthDate() {
        List<Person> sortedByBirthdate = _db.sortedByBirthdate();
        Assert.assertEquals("Brown", sortedByBirthdate.get(0).get_surname());
    }
}
