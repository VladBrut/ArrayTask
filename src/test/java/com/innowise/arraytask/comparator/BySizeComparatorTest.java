package com.innowise.arraytask.comparator;

import com.innowise.arraytask.entity.ArrayContainer;
import com.innowise.arraytask.entity.impl.IntArray;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BySizeComparatorTest {

    @Test
    void testCompare() {
        ArrayContainer c1 = new ArrayContainer(1, "A", new IntArray(new int[]{1, 2, 3}));
        ArrayContainer c2 = new ArrayContainer(2, "B", new IntArray(new int[]{1}));
        BySizeComparator comparator = new BySizeComparator();

        List<ArrayContainer> list = new ArrayList<>(List.of(c1, c2));
        list.sort(comparator);

        assertEquals(1, list.get(0).array().size());
        assertEquals(3, list.get(1).array().size());
    }
}