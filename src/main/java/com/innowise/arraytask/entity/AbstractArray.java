package com.innowise.arraytask.entity;

public abstract class AbstractArray {
    public abstract int size();
    public abstract Number get(int index);
    public abstract void set(int index, Number value);
    public abstract AbstractArray copy();

    @Override
    public abstract String toString();
}