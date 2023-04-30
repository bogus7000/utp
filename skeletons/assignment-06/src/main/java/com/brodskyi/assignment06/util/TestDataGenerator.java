package com.brodskyi.assignment06.util;

import com.brodskyi.assignment06.implementation.*;
import com.brodskyi.assignment06.model.AcademicDegree;
import com.brodskyi.assignment06.model.Nationality;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TestDataGenerator {

    private final static String[] DEPARTMENT_NAMES = {"Department of Computer Science", "Department of Information Management",
            "Department of Graphic Design"};

    private final static String[] STUDENT_GROUP_NAMES = {"Group 1", "Group 2", "Group 3", "Group 4", "Group 5",
            "Group 6", "Group 7", "Group 8", "Group 9", "Group 10", "Group 11", "Group 12"};

    private final static String[] SUBJECT_NAMES = {"Algorithms and Data Structures", "Database Systems",
            "Discrete Mathematics", "Object-Oriented Programming", "Numerical Methods", "Operating Systems",
            "Artificial Intelligence", "Computer Networks", "Computer Graphics", "Web Development"};

    private final Random random = new Random();
    private final Faker faker = new Faker();


    public List<Student> generateStudents(int count) {
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String pesel = PeselGenerator.randomPesel();
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            LocalDate birthDate = generateBirthDate();
            Nationality nationality = Nationality.randomNationality();
            String studentBookNo = "s" + (i + 1);
            students.add(new Student(pesel, lastName, firstName, birthDate, nationality, studentBookNo));
        }
        return students;
    }

    public List<Teacher> generateTeachers(int count) {
        List<Teacher> teachers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String ssn = PeselGenerator.randomPesel();
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            LocalDate birthDate = generateBirthDate();
            Nationality nationality = Nationality.randomNationality();
            AcademicDegree academicDegree = AcademicDegree.randomAcademicDegree();
            LocalDate hireDate = generateHireDate();
            teachers.add(new Teacher(ssn, lastName, firstName, birthDate, nationality, academicDegree, hireDate));
        }
        return teachers;
    }

    public List<Department> generateDepartments(int count, List<Teacher> teachers) {
        List<Department> departments = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String name = DEPARTMENT_NAMES[random.nextInt(DEPARTMENT_NAMES.length)];
            List<Teacher> employees = generateSublist(teachers, 3, 6);
            departments.add(new Department(name, teachers));
        }
        return departments;
    }

    public List<StudentGroup> generateStudentGroups(int count, List<Student> students) {
        List<StudentGroup> studentGroups = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String name = STUDENT_GROUP_NAMES[i];
            List<Student> groupStudents = generateSublist(students, 8, 10);
            studentGroups.add(new StudentGroup(name, groupStudents));
        }
        return studentGroups;
    }

    public List<Subject> generateSubjects(int count, List<Department> departments, List<Teacher> teachers, List<StudentGroup> studentGroups) {
        List<Subject> subjects = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String name = SUBJECT_NAMES[random.nextInt(SUBJECT_NAMES.length)];
            Department supervisingDepartment = departments.get(random.nextInt(departments.size()));
            Teacher lecturer = supervisingDepartment.getEmployees().get(random.nextInt(supervisingDepartment.getEmployees().size()));
            List<Student> attendingStudents = generateSublist(getAllStudents(studentGroups), 10, 30);
            subjects.add(new Subject(name, supervisingDepartment, lecturer, attendingStudents));
        }
        return subjects;
    }


    private LocalDate generateBirthDate() {
        int year = 1960 + random.nextInt(30);
        int month = random.nextInt(12) + 1;
        int day = random.nextInt(28) + 1;
        return LocalDate.of(year, month, day);
    }

    private LocalDate generateHireDate() {
        int year = 2000 + random.nextInt(10);
        int month = random.nextInt(12) + 1;
        int day = random.nextInt(28) + 1;
        return LocalDate.of(year, month, day);
    }

    private <T> List<T> generateSublist(List<T> items, int minSize, int maxSize) {
        int size = minSize + random.nextInt(maxSize - minSize + 1);
        Collections.shuffle(items);
        return items.subList(0, size);
    }

    private List<Student> getAllStudents(List<StudentGroup> studentGroups) {
        List<Student> students = new ArrayList<>();
        for (StudentGroup studentGroup : studentGroups) {
            students.addAll(studentGroup.getStudents());
        }
        return students;
    }

}
