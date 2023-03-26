package com.brodskyi.assignment02.test;

import com.brodskyi.assignment02.implementation.*;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class HumanResourcesStatisticsTest {
    private Manager ceo;
    private Manager manager1;
    private Manager manager2;
    private List<Employee> employees;
    private List<Worker> workers1;
    private List<Worker> workers2;
    private List<Trainee> trainees1;
    private List<Trainee> trainees2;

    @Before
    public void before() {
        ceo = new Manager("John", "Doe", LocalDate.of(1970, 1, 1), BigDecimal.valueOf(10000), LocalDate.of(2000, 1, 1), BigDecimal.valueOf(5000));
        manager1 = new Manager("Alice", "Smith", LocalDate.of(1975, 1, 1), BigDecimal.valueOf(8000), LocalDate.of(2002, 1, 1), BigDecimal.valueOf(3000));
        manager2 = new Manager("Bob", "Brown", LocalDate.of(1978, 1, 1), BigDecimal.valueOf(8000), LocalDate.of(2005, 1, 1), BigDecimal.valueOf(3000));

        employees = new ArrayList<>(Arrays.asList(ceo, manager1, manager2));
        workers1 = new ArrayList<>();
        workers2 = new ArrayList<>();
        trainees1 = new ArrayList<>();
        trainees2 = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            Worker worker = new Worker("Worker" + (i + 1), "Surname", LocalDate.of(1985, 1, 1), BigDecimal.valueOf(5000), manager1, LocalDate.of(2010, 1, 1), BigDecimal.valueOf(1000));
            employees.add(worker);
            workers1.add(worker);
            manager1.addSubordinate(worker);
        }

        Worker workerA = new Worker("Worker" + 7, "Anderson", LocalDate.of(1985, 1, 1), BigDecimal.valueOf(5000), manager1, LocalDate.of(2010, 1, 1), BigDecimal.valueOf(1000));
        employees.add(workerA);
        workers1.add(workerA);
        manager1.addSubordinate(workerA);

        for (int i = 0; i < 6; i++) {
            Worker worker = new Worker("Worker" + (i + 7), "Surname", LocalDate.of(1985, 1, 1), BigDecimal.valueOf(5000), manager2, LocalDate.of(2010, 1, 1), BigDecimal.valueOf(1000));
            employees.add(worker);
            workers2.add(worker);
            manager2.addSubordinate(worker);
        }

        for (int i = 0; i < 2; i++) {
            Trainee trainee = new Trainee("Trainee" + (i + 1), "Surname", LocalDate.of(1995, 1, 1), BigDecimal.valueOf(3000), manager1, LocalDate.of(2021, 1, 1));
            employees.add(trainee);
            trainees1.add(trainee);
            manager1.addSubordinate(trainee);
        }

        for (int i = 0; i < 4; i++) {
            Trainee trainee = new Trainee("Trainee" + (i + 5), "Surname", LocalDate.of(1995, 1, 1), BigDecimal.valueOf(3000), manager2, LocalDate.of(2021, 1, 1));
            employees.add(trainee);
            trainees2.add(trainee);
            manager2.addSubordinate(trainee);
        }
    }

    @Test
    public void testPayroll() {
        List<PayrollEntry> payrollEntries = HumanResourcesStatistics.payroll(employees);
        assertEquals(employees.size(), payrollEntries.size());
    }

    @Test
    public void testSubordinatesPayroll() {
        List<PayrollEntry> payrollEntries = HumanResourcesStatistics.subordinatesPayroll(manager1, employees);
        int totalSubordinatesManagerOne = workers1.size() + trainees1.size();
        assertEquals(totalSubordinatesManagerOne, payrollEntries.size());
    }

    @Test
    public void testBonusTotal() {
        BigDecimal totalBonus = HumanResourcesStatistics.bonusTotal(employees);
        List<PayrollEntry> payrollEntries = HumanResourcesStatistics.payroll(employees);
        BigDecimal totalWithBonus = payrollEntries.stream().map(PayrollEntry::getSalaryPlusBonus).reduce(BigDecimal.ZERO, BigDecimal::add);
        assertEquals(totalWithBonus, totalBonus);
    }

    @Test
    public void testHighestSalaryWithoutBonus() {
        BigDecimal highestSalary = HumanResourcesStatistics.highestSalaryWithoutBonus(employees);
        BigDecimal expectedHighestSalary = ceo.getSalary();
        assertEquals(expectedHighestSalary, highestSalary);
    }

    @Test
    public void testHighestSalaryIncludingBonus() {
        BigDecimal highestSalary = HumanResourcesStatistics.highestSalaryIncludingBonus(employees);
        BigDecimal expectedHighestSalary = ceo.getSalary().add(ceo.getBonus());
        assertEquals(expectedHighestSalary, highestSalary);
    }

    @Test
    public void testSurnameBeginsWithA() {
        List<Employee> aSubordinates = HumanResourcesStatistics.surnameBeginsWithA(manager1, employees);
        assertEquals(1, aSubordinates.size());
    }

    @Test
    public void testEarnMoreThan1000() {
        List<Employee> earningMoreThan1000 = HumanResourcesStatistics.earnMoreThan1000(employees);
        assertEquals(employees.size(), earningMoreThan1000.size());
    }
}
