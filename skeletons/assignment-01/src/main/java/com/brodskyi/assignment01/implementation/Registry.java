package com.brodskyi.assignment01.implementation;

import com.brodskyi.assignment01.api.IAggregable;
import com.brodskyi.assignment01.api.IContainer;
import com.brodskyi.assignment01.api.IDeeplyCloneable;

import java.util.ArrayList;
import java.util.List;

public final class Registry <E extends IAggregable<E, V> & IDeeplyCloneable<E>, V> implements IContainer<E, V> {
    private final List<E> _list = new ArrayList<>();

    public Registry() {
    }

    public void push(E element)
    {
        _list.add(element);
    }

    public List<E> elements()
    {
        return _list;
    }

    public V aggregateAllElements() {
        V value = null;
        for (E element: _list
        ) {
            value = element.aggregate(value);
        }
        return value;
    }

    public E cloneElementAtIndex(int index) {
        E element = _list.get(index);
        return element.deepClone();
    }
}
