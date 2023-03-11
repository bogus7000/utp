package com.brodskyi.assignment01.implementation;


import com.brodskyi.assignment01.api.IAggregable;
import com.brodskyi.assignment01.api.IDeeplyCloneable;

public class Student implements IAggregable<Student, Integer>, IDeeplyCloneable<Student> {
    private final int _tuitionFee;

    public Student (int tuitionFee) {
        _tuitionFee = tuitionFee;
    }

    public int tuitionFee() {
        return _tuitionFee;
    }

    public Integer aggregate(Integer value) {
        if (value == null) {
            return _tuitionFee;
        }
        return _tuitionFee + value;
    }

    public Student deepClone() {
        return new Student(_tuitionFee);
    }
}
