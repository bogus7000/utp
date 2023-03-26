package com.brodskyi.assignment02.implementation;

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

}
