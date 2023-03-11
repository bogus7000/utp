package com.brodskyi.assignment01.test;

import com.brodskyi.assignment01.implementation.Student;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public final class StudentTest {

    private static final int TUITION_FEE = 2000;

    private Student _student;

    @Before
    public void before() {
        _student = new Student(TUITION_FEE);
    }

    @Test
    public void shouldAggregate() {
        int aggregate = _student.aggregate(null);
        Assert.assertEquals(TUITION_FEE, aggregate);
        final int init = 5000;
        Assert.assertEquals(TUITION_FEE + init, (int) (_student.aggregate(init)));
    }

    @Test
    public void shouldDeepClone() {
        Student clone = _student.deepClone();
        Assert.assertEquals(_student.tuitionFee(), clone.tuitionFee());
        Assert.assertNotSame(_student, clone);
    }
}
