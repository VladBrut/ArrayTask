package com.innowise.arraytask.service.impl;

import com.innowise.arraytask.entity.impl.IntArray;
import com.innowise.arraytask.service.ArraySorter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ArraySorterImplTest {
    private final ArraySorter sorter = new ArraySorterImpl();

    @Test
    void testBubbleSort() {
        IntArray array = new IntArray(new int[]{5, 2, 9, 1, 5, 6});
        sorter.bubbleSort(array);
        int[] expected = {1, 2, 5, 5, 6, 9};
        assertArrayEquals(expected, array.getData());
    }

    @Test
    void testQuickSort() {
        IntArray array = new IntArray(new int[]{5, 2, 9, 1, 5, 6});
        sorter.quickSort(array);
        int[] expected = {1, 2, 5, 5, 6, 9};
        assertArrayEquals(expected, array.getData());
    }
}