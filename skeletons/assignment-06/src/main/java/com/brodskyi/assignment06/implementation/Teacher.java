package com.brodskyi.assignment06.implementation;

import com.brodskyi.assignment06.api.Person;
import com.brodskyi.assignment06.model.AcademicDegree;
import com.brodskyi.assignment06.model.Nationality;

import java.time.LocalDate;
import java.util.Objects;

public class Teacher extends Person {
    private final AcademicDegree academicDegree;
    private final LocalDate hireDate;

    public Teacher(String pesel, String surname, String firstName, LocalDate birthDate, Nationality nationality, AcademicDegree academicDegree, LocalDate hireDate) {
        super(pesel, surname, firstName, birthDate, nationality);
        this.academicDegree = academicDegree;
        this.hireDate = hireDate;
    }

    public AcademicDegree getAcademicDegree() {
        return academicDegree;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;
        Teacher teacher = (Teacher) obj;
        return Objects.equals(getAcademicDegree(), teacher.getAcademicDegree()) &&
         Objects.equals(getHireDate(), teacher.getHireDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getAcademicDegree(), getHireDate());
    }

    @Override
    public String toString() {
        return super.toString() + ", Teacher{" +
                "academicDegree=" + academicDegree +
                ", hireDate=" + hireDate +
                '}';
    }
}
