package com.innowise.arraytask.entity.impl;

import com.innowise.arraytask.entity.AbstractArray;

import java.util.Arrays;

public class DoubleArray extends AbstractArray {
    private final double[] data;

    public DoubleArray(double[] data) {
        this.data = data.clone();
    }

    public DoubleArray(int size) {
        this.data = new double[size];
    }

    @Override
    public int size() {
        return data.length;
    }

    @Override
    public Number get(int index) {
        return data[index];
    }

    public double[] getData() {
        return data.clone();
    }

    @Override
    protected void doSet(int index, Number value) {
        data[index] = value.doubleValue();
    }

    @Override
    public AbstractArray copy() {
        return new DoubleArray(data);
    }

    @Override
    public String toString() {
        return Arrays.toString(data);
    }
}