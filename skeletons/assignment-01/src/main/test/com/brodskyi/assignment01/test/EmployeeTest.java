package com.brodskyi.assignment01.test;

import com.brodskyi.assignment01.implementation.Employee;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public final class EmployeeTest {

    private static final int SALARY = 2000;

    private Employee _employee;

    @Before
    public void before() {
        _employee = new Employee(SALARY);
    }

    @Test
    public void shouldAggregate() {
        int aggregate = _employee.aggregate(null);
        Assert.assertEquals(SALARY, aggregate);
        final int init = 3000;
        Assert.assertEquals(SALARY + init, (int) (_employee.aggregate(init)));
    }

    @Test
    public void shouldDeepClone() {
        Employee clone = _employee.deepClone();
        Assert.assertEquals(_employee.salary(), clone.salary());
        Assert.assertNotSame(_employee, clone);
    }
}
