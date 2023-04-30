package com.brodskyi.assignment06.implementation;

import com.brodskyi.assignment06.api.Person;
import com.brodskyi.assignment06.model.Nationality;

import java.time.LocalDate;
import java.util.Objects;

public class Student extends Person {

    private final String studentBookNo;

    public Student(String pesel, String surname, String firstName, LocalDate birthDate, Nationality nationality, String studentBookNo) {
        super(pesel, surname, firstName, birthDate, nationality);
        this.studentBookNo = studentBookNo;
    }

    public String getStudentBookNo() {
        return studentBookNo;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;
        Student student = (Student) obj;
        return Objects.equals(getStudentBookNo(), student.getStudentBookNo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getStudentBookNo());
    }

    @Override
    public String toString() {
        return super.toString() + ", Student{" +
                "studentBookNo='" + studentBookNo + '\'' +
                '}';
    }
}
