package com.brodskyi.assignment08.implementation;

import com.brodskyi.assignment08.exceptions.Assignment08Exception;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.List;

public class DataStreamTest {
    private static final String PATH_PREFIX = "src/main/test/com/brodskyi/assignment08/implementation/";
    private static final String MOCK_FILE_SORTING = PATH_PREFIX + "persons-operations-sorting.txt";

    @Test
    public void shouldSerializeAndDeserializePersonDatabase() throws IOException, ParseException, Assignment08Exception {
        List<Person> _persons = InputParser.parse(MOCK_FILE_SORTING);
        PersonDatabase db = new PersonDatabase(_persons);
        Person person = db.sortedByFirstName().get(0);

        DataOutputStream out = new DataOutputStream(new FileOutputStream("persondb"));
        db.serialize(out);
        out.close();

        DataInputStream in = new DataInputStream(new FileInputStream("persondb"));
        PersonDatabase dbDeserialized = PersonDatabase.deserialize(in);
        in.close();

        List<Person> sortedNaturally = dbDeserialized.sortedByFirstName();
        Person personDeserialized = sortedNaturally.get(0);
        Assert.assertEquals(person.get_firstName(), personDeserialized.get_firstName());
        Assert.assertEquals(person.get_surname(), personDeserialized.get_surname());
        Assert.assertEquals(person.get_birthdate(), personDeserialized.get_birthdate());

        Path path = Paths.get("persondb");
        Files.delete(path);
    }

    @Test
    public void shouldSerializeAndDeserializePerson() throws IOException, ParseException, Assignment08Exception {
        List<Person> _persons = InputParser.parse(MOCK_FILE_SORTING);
        PersonDatabase db = new PersonDatabase(_persons);
        List<Person> sortedNaturally = db.sortedByBirthdate();

        Person person = sortedNaturally.get(0);

        DataOutputStream out = new DataOutputStream(new FileOutputStream("person"));
        person.seralize(out);
        out.close();

        DataInputStream in = new DataInputStream(new FileInputStream("person"));
        Person personDeserialized = Person.deseralize(in);
        in.close();

        Assert.assertEquals(person.get_firstName(), personDeserialized.get_firstName());
        Assert.assertEquals(person.get_surname(), personDeserialized.get_surname());
        Assert.assertEquals(person.get_birthdate(), personDeserialized.get_birthdate());

        Path path = Paths.get("person");
        Files.delete(path);
    }
}
