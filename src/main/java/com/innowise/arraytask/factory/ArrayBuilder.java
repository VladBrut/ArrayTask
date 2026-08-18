package com.innowise.arraytask.factory;

import com.innowise.arraytask.entity.AbstractArray;
import com.innowise.arraytask.entity.impl.DoubleArray;
import com.innowise.arraytask.entity.impl.IntArray;
import com.innowise.arraytask.exception.ArrayProcessingException;

import java.util.ArrayList;
import java.util.List;

public class ArrayBuilder {
    private final List<Number> data = new ArrayList<>();

    public ArrayBuilder clear() {
        data.clear();
        return this;
    }

    public ArrayBuilder addNumber(Number number) {
        data.add(number);
        return this;
    }

    public ArrayBuilder addAllNumbers(List<Number> numbers) {
        data.addAll(numbers);
        return this;
    }

    public AbstractArray build() throws ArrayProcessingException {
        if (data.isEmpty()) {
            return new DoubleArray(0);
        }
        boolean allIntegers = true;
        for (Number num : data) {
            if (!(num instanceof Integer)) {
                if (num instanceof Double) {
                    double d = num.doubleValue();
                    if (d != Math.floor(d)) {
                        allIntegers = false;
                        break;
                    }
                } else {
                    allIntegers = false;
                    break;
                }
            }
        }
        if (allIntegers) {
            int[] intData = new int[data.size()];
            for (int i = 0; i < data.size(); i++) {
                intData[i] = data.get(i).intValue();
            }
            return new IntArray(intData);
        } else {
            double[] doubleData = new double[data.size()];
            for (int i = 0; i < data.size(); i++) {
                doubleData[i] = data.get(i).doubleValue();
            }
            return new DoubleArray(doubleData);
        }
    }
}