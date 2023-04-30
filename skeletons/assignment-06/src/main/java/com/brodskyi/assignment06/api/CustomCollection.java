package com.brodskyi.assignment06.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public abstract class CustomCollection<T> {
    private final Set<T> instances;

    public CustomCollection() {
        instances = new TreeSet<T>();
    }

    public boolean add(T instance) {
        return instances.add(instance);
    }

    public boolean remove(T instance) {
        return instances.remove(instance);
    }

    public List<T> getAll() {
        return new ArrayList<>(instances);
    }

    @Override
    public String toString() {
        return "CustomCollection{" +
                "instances=" + instances +
                '}';
    }
}
