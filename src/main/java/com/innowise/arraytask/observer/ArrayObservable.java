package com.innowise.arraytask.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class ArrayObservable {
    private final List<ArrayObserver> arrayObservers = new ArrayList<>();

    public void addObserver(ArrayObserver arrayObserver) {
        if (!arrayObservers.contains(arrayObserver)) {
            arrayObservers.add(arrayObserver);
        }
    }

    public void removeObserver(ArrayObserver arrayObserver) {
        arrayObservers.remove(arrayObserver);
    }

    protected void notifyObservers(Object arg) {
        for (ArrayObserver arrayObserver : arrayObservers) {
            arrayObserver.update(this, arg);
        }
    }
}