package com.brodskyi.assignment05.api;

import com.brodskyi.assignment05.model.Task;

import java.util.PriorityQueue;

public abstract class AppThread implements Runnable {
    protected final PriorityQueue<Task> _queue;
    protected final int _id;

    public AppThread(PriorityQueue<Task> queue, int id) {
        _queue = queue;
        _id =  id;
    }

    public void printWithId(String message) {
        System.out.println(
                "[Thread " + _id + "]: " + message
        );
    }
}
