package com.brodskyi.assignment03.implementation;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public final class HumanResourcesStatistics {
    public static List<PayrollEntry> payroll(List<Employee> employees) {
        if (employees == null) {
            return null;
        }
        return employees.stream().map(PayrollEntry::new).collect(Collectors.toList());
    }
    public static List<PayrollEntry> subordinatesPayroll(Manager manager, List<Employee> employees) {
        if (manager == null || employees == null) {
            return null;
        }
        return employees.stream().filter(emp -> emp.getManager() == manager).map(PayrollEntry::new).collect(
                Collectors.toList());
    }

    public static BigDecimal bonusTotal(List<Employee> employees) {
        if (employees == null) {
            return null;
        }

        return employees.stream().map(PayrollEntry::new).map(PayrollEntry::getSalaryPlusBonus).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static Employee longestSeniority(List<Employee> employees) {
        // I'm not sure what "seniority" means in this case. Oldest employee? Longest career?
        return null;
    }

    public static BigDecimal highestSalaryWithoutBonus(List<Employee> employees) {
        if (employees == null) {
            return null;
        }

        return employees.stream().map(PayrollEntry::new).map(PayrollEntry::employee).map(Employee::getSalary).max(BigDecimal::compareTo).orElse(BigDecimal.ZERO);
    }

    public static BigDecimal highestSalaryIncludingBonus(List<Employee> employees) {
        if (employees == null) {
            return null;
        }

        return employees.stream().map(PayrollEntry::new).map(PayrollEntry::getSalaryPlusBonus).max(BigDecimal::compareTo).orElse(BigDecimal.ZERO);
    }

    public static List<Employee> surnameBeginsWithA(Manager manager, List<Employee> employees) {
        if (manager == null || employees == null) {
            return null;
        }
        return employees.stream().filter(emp -> emp.getManager() == manager).filter(employee -> employee.getSurname().startsWith("A")).collect(
                Collectors.toList());
    }

    public static List<Employee> earnMoreThan1000(List<Employee> employees) {
        if ( employees == null) {
            return null;
        }

        return employees.stream().filter(employee -> employee.getSalary().compareTo(new BigDecimal(1000)) == 1).collect(
                Collectors.toList());
    }

    public static List<Employee> areOlderThanAndEarnMore(List<Employee> allEmployees, Employee employee) {
        if ( allEmployees == null || employee == null) {
            return null;
        }

        return allEmployees.stream().filter(e -> e.isOlderThan(employee) && e.isSalaryIsGreaterThan(employee.getSalary())).collect(Collectors.toList());
    }

    public static List<Trainee> apprenticeshipLongerThan(List<Employee> allEmployees, int daysCount) {
        if ( allEmployees == null || daysCount <= 0) {
            return null;
        }

        List<Trainee> matches = allEmployees.stream().map(PayrollEntry::new).filter(e -> e.isTrainee() && e.getTrainee().isApprenticeshipDaysLongerThan((short) daysCount)).map(PayrollEntry::getTrainee).collect(Collectors.toList());
        matches.forEach(t -> t.setSalary(t.getSalary().multiply(BigDecimal.valueOf(1.05))));
        return matches;
    }

    public static List<Worker> seniorityLongerThan(List<Employee> allEmployees, int monthCount) {
        if ( allEmployees == null || monthCount <= 0) {
            return null;
        }

        List<Worker> matches = allEmployees.stream().map(PayrollEntry::new).filter(e -> e.isWorker() && e.getWorker().isSeniorityLongerThanMonths((short)monthCount) && !e.getWorker().hasBonusGreaterThan(BigDecimal.valueOf(300))).map(PayrollEntry::getWorker).collect(Collectors.toList());
        matches.forEach(w -> w.setBonus(BigDecimal.valueOf(300)));
        return matches;
    }

    public static List<Worker> seniorityBetweenOneAndThreeYears(List<Employee> allEmployees) {
        if ( allEmployees == null) {
            return null;
        }

        List<Worker> matches = allEmployees.stream().map(PayrollEntry::new).filter(e -> e.isWorker() && e.getWorker().isSeniorityLongerThanYears((short)1) && e.getWorker().isSeniorityShorterThanYears((short)3)).map(PayrollEntry::getWorker).collect(Collectors.toList());
        matches.forEach(w -> w.setSalary(w.getSalary().multiply(BigDecimal.valueOf(1.1))));
        return matches;
    }

    public static List<Worker> seniorityLongerThanAlign(List<Employee> allEmployees, Worker worker) {
        if ( allEmployees == null || worker == null) {
            return null;
        }

        List<Worker> matches = allEmployees.stream().map(PayrollEntry::new).filter(e -> e.isWorker() && e.getWorker().isSeniorityLongerThanWorker(worker) && e.getWorker().getSalary().compareTo(worker.getSalary()) == -1).map(PayrollEntry::getWorker).collect(Collectors.toList());
        matches.forEach(w -> w.setSalary(worker.getSalary()));
        return matches;
    }

    public static List<Worker> seniorityBetweenTwoAndFourYearsAndAgeGreaterThan(List<Employee> allEmployees, int age) {
        if ( allEmployees == null || age <= 0) {
            return null;
        }

        return allEmployees.stream().map(PayrollEntry::new).filter(e -> e.isWorker() && e.getWorker().isSeniorityLongerThanYears((short)2) && e.getWorker().isSeniorityShorterThanYears((short)4) && e.getWorker().isOlderThan((short)age)).map(PayrollEntry::getWorker).collect(Collectors.toList());
    }
}
