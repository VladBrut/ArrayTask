package com.innowise.arraytask.specification.impl;

import com.innowise.arraytask.entity.ArrayContainer;
import com.innowise.arraytask.entity.impl.IntArray;
import com.innowise.arraytask.specification.ComparisonOperator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MinValueSpecificationTest {

    @Test
    void testMatchesGreater() {
        IntArray array = new IntArray(new int[]{1, 5, 3});
        ArrayContainer container = new ArrayContainer(1, "test", array);
        MinValueSpecification spec = new MinValueSpecification(0, ComparisonOperator.GREATER);
        assertTrue(spec.matches(container));
        spec = new MinValueSpecification(6, ComparisonOperator.GREATER);
        assertFalse(spec.matches(container));
    }

    @Test
    void testMatchesLess() {
        IntArray array = new IntArray(new int[]{10, 20});
        ArrayContainer container = new ArrayContainer(1, "test", array);
        MinValueSpecification spec = new MinValueSpecification(15, ComparisonOperator.LESS);
        assertTrue(spec.matches(container));
        spec = new MinValueSpecification(5, ComparisonOperator.LESS);
        assertFalse(spec.matches(container));
    }

    @Test
    void testMatchesEqual() {
        IntArray array = new IntArray(new int[]{7, 7, 7});
        ArrayContainer container = new ArrayContainer(1, "test", array);
        MinValueSpecification spec = new MinValueSpecification(7, ComparisonOperator.EQUAL);
        assertTrue(spec.matches(container));
        spec = new MinValueSpecification(8, ComparisonOperator.EQUAL);
        assertFalse(spec.matches(container));
    }

    @Test
    void testEmptyArray() {
        IntArray empty = new IntArray(0);
        ArrayContainer container = new ArrayContainer(1, "empty", empty);
        MinValueSpecification spec = new MinValueSpecification(0, ComparisonOperator.GREATER);
        assertFalse(spec.matches(container));
    }
}