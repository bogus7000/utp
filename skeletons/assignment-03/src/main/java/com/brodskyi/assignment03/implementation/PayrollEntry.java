package com.brodskyi.assignment03.implementation;

import java.math.BigDecimal;

public final class PayrollEntry {

    private final Employee _employee;
    private final boolean _isWorker;


    private final boolean _isTrainee;
    private final Worker _worker;
    private final Trainee _trainee;


    private final BigDecimal _salaryPlusBonus;
    public PayrollEntry(Employee employee) {
        _employee = employee;
        BigDecimal salary = employee.getSalary();
        if (Trainee.class == employee.getClass()) {
            _salaryPlusBonus = salary;
            _isWorker = false;
            _worker = null;
            _isTrainee = true;
            _trainee = ((Trainee) employee);
        } else {
            BigDecimal bonus = ((Worker) employee).getBonus();
            if (salary != null && bonus != null) {
                _salaryPlusBonus = salary.add(bonus);
                _isWorker = true;
                _worker = ((Worker) employee);

            } else {
                _salaryPlusBonus = salary;
                _isWorker = false;
                _worker = null;
            }
            _isTrainee = false;
            _trainee = null;
        }
    }

    public BigDecimal getSalaryPlusBonus() {
        return _salaryPlusBonus;
    }

    public Employee employee() {
        return _employee;
    }

    public  boolean isWorker() {
        return _isWorker;
    }

    public Worker getWorker() {
        return _worker;
    }

    public boolean isTrainee() {
        return _isTrainee;
    }

    public Trainee getTrainee() {
        return _trainee;
    }
}
