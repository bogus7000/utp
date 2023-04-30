package com.brodskyi.assignment06.api;

import com.brodskyi.assignment06.model.Nationality;
import com.brodskyi.assignment06.util.PeselValidator;

import java.text.Collator;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Objects;

public abstract class Person implements Comparable<Person> {

    private final String pesel;
    private final String surname;
    private final String firstName;
    private final LocalDate birthDate;
    private final Nationality nationality;

    public Person(String pesel, String surname, String firstName, LocalDate birthDate,  Nationality nationality) {
        if (!new PeselValidator(pesel).isValid()) throw new IllegalArgumentException("[Person] Wrong PESEL number format");
        this.pesel = pesel;
        this.surname = surname;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.nationality = nationality;
    }

    public String getPesel() {
        return pesel;
    }

    public String getSurname() {
        return surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Nationality getNationality() {
        return nationality;
    }

    @Override
    public int compareTo(Person other) {
        return Collator.getInstance(new Locale("pl")).compare(this.surname, other.surname);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return Objects.equals(getPesel(), person.getPesel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPesel());
    }

    @Override
    public String toString() {
        return "Person{" +
                "pesel='" + pesel + '\'' +
                ", surname='" + surname + '\'' +
                ", firstName='" + firstName + '\'' +
                ", birthDate=" + birthDate +
                ", nationality=" + nationality +
                '}';
    }
}