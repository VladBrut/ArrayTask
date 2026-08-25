package com.innowise.arraytask.comparator;

import com.innowise.arraytask.entity.ArrayContainer;

import java.util.Comparator;

public class ByFirstElementComparator implements Comparator<ArrayContainer> {
    @Override
    public int compare(ArrayContainer o1, ArrayContainer o2) {
        double first1 = o1.array().size() > 0 ? o1.array().get(0).doubleValue() : Double.NaN;
        double first2 = o2.array().size() > 0 ? o2.array().get(0).doubleValue() : Double.NaN;
        return Double.compare(first1, first2);
    }
}
