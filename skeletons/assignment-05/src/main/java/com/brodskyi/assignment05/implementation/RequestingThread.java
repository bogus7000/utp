package com.brodskyi.assignment05.implementation;

import com.brodskyi.assignment05.api.AppThread;
import com.brodskyi.assignment05.model.Task;
import com.brodskyi.assignment05.model.TaskPriority;

import java.util.PriorityQueue;

public class RequestingThread extends AppThread {
    private boolean waiting = false;
    public RequestingThread(PriorityQueue<Task> _queue, int id) {
        super(_queue, id);
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            synchronized (_queue) {
                if (!waiting) {
                    // Request
                    Task task = new Task(TaskPriority.randomPriority(), (int) (Math.random() * 100), (int) (Math.random() * 100), _id);
                    _queue.offer(task);
                    this.printWithId("Requested " + task.getPriority() + " priority task. Task ID: " + task.getTaskId());
                    _queue.notifyAll();
                    waiting = true;

                    // Wait
                    try {
                        _queue.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                // Check
                Task candidate = _queue.peek();
                if (candidate != null && candidate.getResult() > 0 && candidate.getRequestingThreadId() == _id) {
                    // Remove
                    Task processed = _queue.poll();
                    if (processed != null) {
                        this.printWithId("Received result for task with ID: " + processed.getTaskId());
                        _queue.notifyAll();
                        waiting = false;
                        try {
                            Thread.sleep((int) (Math.random() * 100));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}

