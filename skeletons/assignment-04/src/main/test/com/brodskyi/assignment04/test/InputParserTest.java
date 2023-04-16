package com.brodskyi.assignment04.test;

import com.brodskyi.assignment04.implementation.InputParser;
import com.brodskyi.assignment04.implementation.Person;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;

public class InputParserTest {
    private static final String PATH_PREFIX = "src/main/test/com/brodskyi/assignment04/test/";
    private static final String MOCK_FILE_PERSONS_VALID = PATH_PREFIX + "persons.txt";
    private static final String MOCK_FILE_PERSONS_EMPTY = PATH_PREFIX + "persons-empty.txt";
    private static final String MOCK_FILE_PERSONS_INVALID_DATE = PATH_PREFIX + "persons-validation-date.txt";
    private static final String MOCK_FILE_PERSONS_INVALID_NAME = PATH_PREFIX + "persons-validation-name.txt";
    private static final String MOCK_FILE_PERSONS_INVALID_SURNAME = PATH_PREFIX + "persons-validation-surname.txt";
    private static final String MOCK_FILE_DOES_NOT_EXIST = PATH_PREFIX + "stub.txt";
    private List<Person> _persons;

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotParseEmptyFiles() throws FileNotFoundException, ParseException {
        _persons = InputParser.parse(MOCK_FILE_PERSONS_EMPTY);
    }

    @Test(expected = FileNotFoundException.class)
    public void shouldFailWhenFileDoesNotExist() throws FileNotFoundException, ParseException {
        _persons = InputParser.parse(MOCK_FILE_DOES_NOT_EXIST);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenDateFormatIsWrong() throws FileNotFoundException, ParseException {
        _persons = InputParser.parse(MOCK_FILE_PERSONS_INVALID_DATE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenNameFormatIsWrong() throws FileNotFoundException, ParseException {
        _persons = InputParser.parse(MOCK_FILE_PERSONS_INVALID_NAME);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenSurnameFormatIsWrong() throws FileNotFoundException, ParseException {
        _persons = InputParser.parse(MOCK_FILE_PERSONS_INVALID_SURNAME);
    }

    @Test public void shouldParsePersonsFromFile() throws FileNotFoundException, ParseException {
        _persons = InputParser.parse(MOCK_FILE_PERSONS_VALID);
        System.out.println(_persons);
        for (Person p: _persons) {
            System.out.println(p.toString());
        }
        Assert.assertEquals(2, _persons.size());
        Person p = _persons.get(0);
        Assert.assertEquals("John", p.get_firstName());
        Assert.assertEquals("Smith", p.get_surname());
    }

}
