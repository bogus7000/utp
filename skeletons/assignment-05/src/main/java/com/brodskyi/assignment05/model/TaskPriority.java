package com.brodskyi.assignment05.model;

import java.util.Random;

public enum TaskPriority {
    LOW, NORMAL, HIGH;

    private static final Random PRNG = new Random();

    public static TaskPriority randomPriority() {
        TaskPriority[] priorities = values();
        return priorities[PRNG.nextInt(priorities.length)];
    }
}
