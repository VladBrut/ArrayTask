package com.innowise.arraytask.comparator;

import com.innowise.arraytask.entity.ArrayContainer;
import com.innowise.arraytask.entity.impl.IntArray;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ByNameComparatorTest {

    @Test
    void testCompare() {
        ArrayContainer c1 = new ArrayContainer(1, "Zebra", new IntArray(new int[]{1}));
        ArrayContainer c2 = new ArrayContainer(2, "Alpha", new IntArray(new int[]{2}));
        ByNameComparator comparator = new ByNameComparator();

        List<ArrayContainer> list = new ArrayList<>(List.of(c1, c2));
        list.sort(comparator);

        assertEquals("Alpha", list.get(0).name());
        assertEquals("Zebra", list.get(1).name());
    }
}