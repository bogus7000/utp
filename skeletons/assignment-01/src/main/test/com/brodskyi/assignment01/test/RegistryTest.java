package com.brodskyi.assignment01.test;

import com.brodskyi.assignment01.implementation.Employee;
import com.brodskyi.assignment01.implementation.Registry;
import com.brodskyi.assignment01.implementation.Student;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public final class RegistryTest {
    private static final int BASE_SAL = 2000;
    private static final Employee emp0 = new Employee(BASE_SAL);
    private static final Employee emp1 = new Employee(BASE_SAL + 200);
    private static final Employee emp2 = new Employee(BASE_SAL + 400);
    private Registry<Employee, Integer> _employeeRegistry;
    private static final int BASE_FEE = 5000;
    private static final Student stud0 = new Student(BASE_FEE);
    private static final Student stud1 = new Student(BASE_FEE + 1000);
    private static final Student stud2 = new Student(BASE_FEE + 1500);
    private Registry<Student, Integer> _studentRegistry;

    @Before
    public void before() {
        _employeeRegistry = new Registry<>();
        _employeeRegistry.push(emp0);
        _employeeRegistry.push(emp1);
        _employeeRegistry.push(emp2);
        _studentRegistry = new Registry<>();
        _studentRegistry.push(stud0);
        _studentRegistry.push(stud1);
        _studentRegistry.push(stud2);
    }

    @Test
    public void shouldAddElementsToRegistry() {
        _employeeRegistry = new Registry<>();
        _employeeRegistry.push(emp0);
        Assert.assertEquals(_employeeRegistry.elements().get(0), emp0);
        _studentRegistry = new Registry<>();
        _studentRegistry.push(stud0);
        Assert.assertEquals(_studentRegistry.elements().get(0), stud0);
    }

    @Test
    public void shouldReturnAggregate() {
        int aggregate = _employeeRegistry.aggregateAllElements();
        Assert.assertEquals(6600, aggregate);
        int aggregate2 = _studentRegistry.aggregateAllElements();
        Assert.assertEquals(17500, aggregate2);
    }

    @Test
    public void shouldReturnListOfElements() {
        List<Employee> testList = new ArrayList<>();
        testList.add(emp0);
        testList.add(emp1);
        testList.add(emp2);
        List<Employee> registryList = _employeeRegistry.elements();
        Assert.assertEquals(testList.size(), registryList.size());
        Assert.assertEquals(testList.get(0).salary(), registryList.get(0).salary());
        Assert.assertEquals(testList.get(1).salary(), registryList.get(1).salary());
        Assert.assertEquals(testList.get(2).salary(), registryList.get(2).salary());
        List<Student> testList2 = new ArrayList<>();
        testList2.add(stud0);
        testList2.add(stud1);
        testList2.add(stud2);
        List<Student> registryList2 = _studentRegistry.elements();
        Assert.assertEquals(testList2.size(), registryList2.size());
        Assert.assertEquals(testList2.get(0).tuitionFee(), registryList2.get(0).tuitionFee());
        Assert.assertEquals(testList2.get(1).tuitionFee(), registryList2.get(1).tuitionFee());
        Assert.assertEquals(testList2.get(2).tuitionFee(), registryList2.get(2).tuitionFee());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldThrowExceptionIfRegistryIndexIsOutOfBounds() {
        Employee clone = _employeeRegistry.cloneElementAtIndex(4);
    }

    @Test
    public void shouldReturnDeepCloneAtIndex() {
        Employee clone = _employeeRegistry.cloneElementAtIndex(0);
        Assert.assertEquals(clone.salary(), emp0.salary());
        Assert.assertNotSame(clone, emp0);
        Student clone2 = _studentRegistry.cloneElementAtIndex(0);
        Assert.assertEquals(clone2.tuitionFee(), stud0.tuitionFee());
        Assert.assertNotSame(clone2, stud0);
    }
}
