package com.brodskyi.assignment06.implementation;

import java.util.List;

public class Subject {
    private final String name;
    private final Department supervisingDepartment;
    private final Teacher lecturer;
    private final List<Student> attendingStudents;

    public Subject(String name, Department supervisingDepartment, Teacher lecturer, List<Student> attendingStudents)
    {
        this.name = name;
        this.supervisingDepartment = supervisingDepartment;
        this.lecturer = lecturer;
        this.attendingStudents = attendingStudents;
    }

    public String getName() {
        return name;
    }

    public Department getSupervisingDepartment() {
        return supervisingDepartment;
    }

    public Teacher getLecturer() {
        return lecturer;
    }

    public List<Student> getAttendingStudents() {
        return attendingStudents;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "name='" + name + '\'' +
                ", supervisingDepartment=" + supervisingDepartment +
                ", lecturer=" + lecturer +
                ", attendingStudents=" + attendingStudents +
                '}';
    }
}
