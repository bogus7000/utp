package com.brodskyi.assignment06.implementation;

import com.brodskyi.assignment06.model.Nationality;
import com.brodskyi.assignment06.util.PeselGenerator;
import com.brodskyi.assignment06.util.TestDataGenerator;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;


public class StudentTest {
    private final TestDataGenerator generator = new TestDataGenerator();

    @Test
    public void shouldNotBeEqual() {
        List<Student> students = generator.generateStudents(2);
        Assert.assertEquals(false, students.get(0).equals(students.get(1)));
        Assert.assertNotEquals(students.get(0).hashCode(), students.get(1).hashCode());
    }

    @Test
    public void shouldBeEqual() {
        String pesel = PeselGenerator.randomPesel();
        String firstName = "John";
        String lastName = "Doe";
        LocalDate birthDate = LocalDate.of(2000, 1, 1);
        Nationality nationality = Nationality.randomNationality();
        String studentBookNo = "s1";
        Student student1 = new Student(pesel, lastName, firstName, birthDate, nationality, studentBookNo);
        Student student2 = new Student(pesel, lastName, firstName, birthDate, nationality, studentBookNo);
        Assert.assertEquals(true, student1.equals(student2));
        Assert.assertEquals(student1.hashCode(), student2.hashCode());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgument() {
        String pesel = "99990099sasd";
        String firstName = "John";
        String lastName = "Doe";
        LocalDate birthDate = LocalDate.of(2000, 1, 1);
        Nationality nationality = Nationality.randomNationality();
        String studentBookNo = "s1";
        Student student1 = new Student(pesel, lastName, firstName, birthDate, nationality, studentBookNo);
    }

    @Test
    public void shouldSkipDuplicates() {
        PersonsCollection<Student> collection = new PersonsCollection<Student>();
        String pesel = PeselGenerator.randomPesel();
        String firstName = "John";
        String lastName = "Doe";
        LocalDate birthDate = LocalDate.of(2000, 1, 1);
        Nationality nationality = Nationality.randomNationality();
        String studentBookNo = "s1";
        Student student1 = new Student(pesel, lastName, firstName, birthDate, nationality, studentBookNo);
        Student student2 = new Student(pesel, lastName, firstName, birthDate, nationality, studentBookNo);
        collection.add(student1);
        collection.add(student2);
        collection.add(student2);
        Assert.assertEquals(1, collection.getAll().size());
    }

    @Test
    public void shouldSortPl() {
        PersonsCollection<Student> collection = new PersonsCollection<Student>();
        List<Student> students = generator.generateStudents(20);
        for (Student student : students) {
            collection.add(student);
        }
        System.out.println(collection.getAll());
        Collections.sort(collection.getAll());
        System.out.println(collection.getAll());
    }

    @Test
    public void shouldSortCustom() {
        PersonsCollection<Student> collection = new PersonsCollection<Student>();
        List<Student> students = generator.generateStudents(20);
        String pesel = PeselGenerator.randomPesel();
        String firstName = "John";
        String lastName = "Doe";
        LocalDate birthDate = LocalDate.of(2000, 1, 1);
        Nationality nationality = Nationality.BELARUSIAN;
        String studentBookNo = "s11111";
        Student student1 = new Student(pesel, lastName, firstName, birthDate, nationality, studentBookNo);
        for (Student student : students) {
            collection.add(student);
        }
        collection.add(student1);
        System.out.println(collection.getAll());
        List<Student> results = collection.filterAndSortByNationality(Nationality.BELARUSIAN);
        System.out.println(results);
    }
}