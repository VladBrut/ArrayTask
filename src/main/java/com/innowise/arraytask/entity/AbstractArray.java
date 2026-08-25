package com.innowise.arraytask.entity;

import com.innowise.arraytask.event.ArrayChangeEvent;
import com.innowise.arraytask.observer.ArrayObservable;
import com.innowise.arraytask.service.impl.ArrayStatisticsImpl;

import java.util.Optional;

public abstract class AbstractArray extends ArrayObservable {
    public abstract int size();

    public abstract Number get(int index);

    public final void set(int index, Number value) {
        Number oldValue = get(index);
        doSet(index, value);
        notifyObservers(new ArrayChangeEvent(index, oldValue, value));
    }

    protected abstract void doSet(int index, Number value);

    public abstract AbstractArray copy();

    public Optional<Double> min() {
        return new ArrayStatisticsImpl().min(this);
    }

    public Optional<Double> max() {
        return new ArrayStatisticsImpl().max(this);
    }

    public Optional<Double> sum() {
        return new ArrayStatisticsImpl().sum(this);
    }

    public Optional<Double> average() {
        return new ArrayStatisticsImpl().average(this);
    }

    @Override
    public abstract String toString();
}