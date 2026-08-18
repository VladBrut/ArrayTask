package com.innowise.arraytask.service.impl;


import com.innowise.arraytask.entity.AbstractArray;
import com.innowise.arraytask.service.ArraySorter;

public class ArraySorterImpl implements ArraySorter {
    @Override
    public void bubbleSort(AbstractArray array) {
        int n = array.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array.get(j).doubleValue() > array.get(j + 1).doubleValue()) {
                    swap(array, j, j + 1);
                }
            }
        }
    }

    @Override
    public void quickSort(AbstractArray array) {
        quickSort(array, 0, array.size() - 1);
    }

    private void quickSort(AbstractArray array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }
    }

    private int partition(AbstractArray array, int low, int high) {
        double pivot = array.get(high).doubleValue();
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array.get(j).doubleValue() <= pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }

    private void swap(AbstractArray array, int i, int j) {
        Number temp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, temp);
    }
}