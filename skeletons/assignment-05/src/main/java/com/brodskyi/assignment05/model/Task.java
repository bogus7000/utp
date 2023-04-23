package com.brodskyi.assignment05.model;

import java.util.UUID;

public class Task {
    private final TaskPriority _priority;
    private final int _param1;
    private final int _param2;
    private final UUID _taskId = UUID.randomUUID();
    private final int _requestingThreadId;
    private int _result = 0;

    public Task(TaskPriority priority, int param1, int param2, int requestingThreadId) {
        _priority = priority;
        _param1 = param1;
        _param2 = param2;
        _requestingThreadId = requestingThreadId;
    }

    public TaskPriority getPriority() {
        return _priority;
    }

    public int getParam1() {
        return _param1;
    }

    public int getParam2() {
        return _param2;
    }

    public UUID getTaskId() {
        return _taskId;
    }

    public int getRequestingThreadId() {
        return _requestingThreadId;
    }

    public int getResult() {
        return _result;
    }

    public void setResult(int _result) {
        this._result = _result;
    }
}
