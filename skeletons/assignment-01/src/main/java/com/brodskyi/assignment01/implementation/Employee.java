package com.brodskyi.assignment01.implementation;

import com.brodskyi.assignment01.api.IAggregable;
import com.brodskyi.assignment01.api.IDeeplyCloneable;

public final class Employee implements IAggregable<Employee, Integer>, IDeeplyCloneable<Employee> {
    private final int _salary;

    public Employee(int salary) {
        _salary = salary;
    }

    public int salary() {
        return _salary;
    }

    public Integer aggregate(Integer value) {
        if (value == null) {
            return _salary;
        }
        return _salary + value;
    }

    public Employee deepClone() {
        return new Employee(_salary);
    }
}
