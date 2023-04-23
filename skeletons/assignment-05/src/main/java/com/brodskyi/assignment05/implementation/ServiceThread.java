package com.brodskyi.assignment05.implementation;

import com.brodskyi.assignment05.api.AppThread;
import com.brodskyi.assignment05.model.Task;

import java.util.PriorityQueue;

public class ServiceThread extends AppThread {
    public ServiceThread(PriorityQueue<Task> _queue, int id) {
        super(_queue, id);
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            Task task;
            synchronized (_queue) {
                task = _queue.peek();
                if (task != null && task.getResult() == 0) {
                    synchronized (_queue) {
                        _queue.poll();
                        task.setResult(task.getParam1() + task.getParam2());
                        _queue.offer(task);
                        this.printWithId("Processed " + task.getPriority() + " priority task with ID: " + task.getTaskId());
                        _queue.notifyAll();
                    }
                }
            }
        }
    }
}
