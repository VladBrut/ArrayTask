package com.innowise.arraytask.entity.impl;

import com.innowise.arraytask.entity.AbstractArray;

import java.util.Arrays;

public class IntArray extends AbstractArray {
    private final int[] data;

    public IntArray(int[] data) {
        this.data = data.clone();
    }

    public IntArray(int size) {
        this.data = new int[size];
    }

    @Override
    public int size() {
        return data.length;
    }

    @Override
    public Number get(int index) {
        return data[index];
    }

    @Override
    public void set(int index, Number value) {
        data[index] = value.intValue();
    }

    @Override
    public AbstractArray copy() {
        return new IntArray(data);
    }

    @Override
    public String toString() {
        return Arrays.toString(data);
    }

    public int[] getData() {
        return data.clone();
    }
}
