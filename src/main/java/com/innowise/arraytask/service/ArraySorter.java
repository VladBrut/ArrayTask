package com.innowise.arraytask.service;


import com.innowise.arraytask.entity.AbstractArray;

public interface ArraySorter {
    void bubbleSort(AbstractArray array);
    void quickSort(AbstractArray array);
}
