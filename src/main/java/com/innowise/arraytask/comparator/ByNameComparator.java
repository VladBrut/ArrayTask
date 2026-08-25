package com.innowise.arraytask.comparator;

import com.innowise.arraytask.entity.ArrayContainer;

import java.util.Comparator;

public class ByNameComparator implements Comparator<ArrayContainer> {
    @Override
    public int compare(ArrayContainer o1, ArrayContainer o2) {
        return o1.name().compareTo(o2.name());
    }
}
