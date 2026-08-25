package com.innowise.arraytask.comparator;

import com.innowise.arraytask.entity.ArrayContainer;
import com.innowise.arraytask.entity.impl.IntArray;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ByIdComparatorTest {

    @Test
    void testCompare() {
        ArrayContainer c1 = new ArrayContainer(5, "A", new IntArray(new int[]{1}));
        ArrayContainer c2 = new ArrayContainer(3, "B", new IntArray(new int[]{2}));
        ByIdComparator comparator = new ByIdComparator();

        List<ArrayContainer> list = new ArrayList<>(List.of(c1, c2));
        list.sort(comparator);

        assertEquals(3, list.get(0).id());
        assertEquals(5, list.get(1).id());
    }
}