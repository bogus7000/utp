package com.brodskyi.assignment06.model;

import java.util.Random;

public enum AcademicDegree {
        Associate, Bachelor, Master, Doctor;

    private static final Random random = new Random();

    public static AcademicDegree randomAcademicDegree() {
        AcademicDegree[] academicDegrees = values();
        return academicDegrees[random.nextInt(academicDegrees.length)];
    }
}
