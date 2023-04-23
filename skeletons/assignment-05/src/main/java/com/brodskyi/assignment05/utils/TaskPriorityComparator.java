package com.brodskyi.assignment05.utils;

import com.brodskyi.assignment05.model.Task;
import com.brodskyi.assignment05.model.TaskPriority;

import java.util.Comparator;

public class TaskPriorityComparator implements Comparator<Task> {
    @Override
    public int compare(Task o1, Task o2) {
        if (o1.getPriority() == TaskPriority.LOW) {
            if (o2.getPriority() == TaskPriority.LOW) {
                return 0;
            } else {
                return 1;
            }
        } else if (o1.getPriority() == TaskPriority.NORMAL) {
            if (o2.getPriority() == TaskPriority.LOW) {
                return -1;
            } else if (o2.getPriority() == TaskPriority.NORMAL){
                return 0;
            } else {
                return 1;
            }
        } else {
            if (o2.getPriority() == TaskPriority.HIGH) {
                return 0;
            } else {
                return -1;
            }
        }
    }
}
