package com.innowise.arraytask.comparator;

import com.innowise.arraytask.entity.ArrayContainer;

import java.util.Comparator;

public class ByIdComparator implements Comparator<ArrayContainer> {
    @Override
    public int compare(ArrayContainer o1, ArrayContainer o2) {
        return Integer.compare(o1.id(), o2.id());
    }
}

