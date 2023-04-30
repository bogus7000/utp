package com.brodskyi.assignment06.implementation;
import java.util.List;

public class StudentGroup {
    private final String name;
    private final List<Student> students;

    public StudentGroup(String name, List<Student> students) {
        this.name = name;
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public List<Student> getStudents() {
        return students;
    }

    @Override
    public String toString() {
        return "StudentGroup{" +
                "name='" + name + '\'' +
                ", students=" + students +
                '}';
    }
}
