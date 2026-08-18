package com.innowise.arraytask.service.impl;

import com.innowise.arraytask.entity.impl.DoubleArray;
import com.innowise.arraytask.entity.impl.IntArray;
import com.innowise.arraytask.service.ArrayStatistics;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArrayStatisticsImplTest {
    private final ArrayStatistics statistics = new ArrayStatisticsImpl();

    @Test
    void testMinMaxSumAverage_IntArray() {
        IntArray array = new IntArray(new int[]{1, 2, 3, 4, 5});
        assertEquals(Optional.of(1.0), statistics.min(array));
        assertEquals(Optional.of(5.0), statistics.max(array));
        assertEquals(Optional.of(15.0), statistics.sum(array));
        assertEquals(Optional.of(3.0), statistics.average(array));
    }

    @Test
    void testMinMaxSumAverage_DoubleArray() {
        DoubleArray array = new DoubleArray(new double[]{1.5, 2.5, 3.5});
        assertEquals(Optional.of(1.5), statistics.min(array));
        assertEquals(Optional.of(3.5), statistics.max(array));
        assertEquals(Optional.of(7.5), statistics.sum(array));
        assertEquals(Optional.of(2.5), statistics.average(array));
    }

    @Test
    void testEmptyArray() {
        DoubleArray empty = new DoubleArray(0);
        assertEquals(Optional.empty(), statistics.min(empty));
        assertEquals(Optional.empty(), statistics.max(empty));
        assertEquals(Optional.of(0.0), statistics.sum(empty));
        assertEquals(Optional.empty(), statistics.average(empty));
    }
}