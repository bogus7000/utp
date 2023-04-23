package com.brodskyi.assignment05.implementation;

import com.brodskyi.assignment05.model.Task;
import com.brodskyi.assignment05.utils.TaskPriorityComparator;

import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static final int NUM_REQUESTING_THREADS = 8;
    private static final int NUM_SERVICE_THREADS = 2;
    private static final int NUM_TASKS = 10;
    private static final int NUM_THREADS = NUM_REQUESTING_THREADS + NUM_SERVICE_THREADS;
    private static final PriorityQueue<Task> taskQueue = new PriorityQueue<Task>(NUM_TASKS, new TaskPriorityComparator());

    public static void main(String[] args) {
        try (ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS)) {

            for (int i = 0; i < NUM_REQUESTING_THREADS; i++) {
                executorService.submit(new RequestingThread(taskQueue, i));
            }

            for (int i = NUM_REQUESTING_THREADS; i < NUM_REQUESTING_THREADS + NUM_SERVICE_THREADS; i++) {
                executorService.submit(new ServiceThread(taskQueue, i));
            }
        }
    }
}