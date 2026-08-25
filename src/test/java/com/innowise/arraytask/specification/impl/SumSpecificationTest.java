package com.innowise.arraytask.specification.impl;

import com.innowise.arraytask.entity.ArrayContainer;
import com.innowise.arraytask.entity.impl.IntArray;
import com.innowise.arraytask.specification.ComparisonOperator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SumSpecificationTest {

    @Test
    void testMatchesGreater() {
        IntArray array = new IntArray(new int[]{1, 2, 3, 4});
        ArrayContainer container = new ArrayContainer(1, "test", array);
        SumSpecification spec = new SumSpecification(9, ComparisonOperator.GREATER);
        assertTrue(spec.matches(container));
    }

    @Test
    void testMatchesLess() {
        IntArray array = new IntArray(new int[]{1, 2, 3});
        ArrayContainer container = new ArrayContainer(1, "test", array);
        SumSpecification spec = new SumSpecification(10, ComparisonOperator.LESS);
        assertTrue(spec.matches(container));
    }

    @Test
    void testMatchesEqual() {
        IntArray array = new IntArray(new int[]{2, 2, 2});
        ArrayContainer container = new ArrayContainer(1, "test", array);
        SumSpecification spec = new SumSpecification(6, ComparisonOperator.EQUAL);
        assertTrue(spec.matches(container));
    }
}