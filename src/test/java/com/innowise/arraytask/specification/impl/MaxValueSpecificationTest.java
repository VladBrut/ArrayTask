package com.innowise.arraytask.specification.impl;

import com.innowise.arraytask.entity.ArrayContainer;
import com.innowise.arraytask.entity.impl.IntArray;
import com.innowise.arraytask.specification.ComparisonOperator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MaxValueSpecificationTest {

    @Test
    void testMatchesGreater() {
        IntArray array = new IntArray(new int[]{2, 8, 4});
        ArrayContainer container = new ArrayContainer(1, "test", array);
        MaxValueSpecification spec = new MaxValueSpecification(5, ComparisonOperator.GREATER);
        assertTrue(spec.matches(container));
        spec = new MaxValueSpecification(10, ComparisonOperator.GREATER);
        assertFalse(spec.matches(container));
    }

    @Test
    void testMatchesLess() {
        IntArray array = new IntArray(new int[]{10, 20});
        ArrayContainer container = new ArrayContainer(1, "test", array);
        MaxValueSpecification spec = new MaxValueSpecification(25, ComparisonOperator.LESS);
        assertTrue(spec.matches(container));
        spec = new MaxValueSpecification(15, ComparisonOperator.LESS);
        assertFalse(spec.matches(container));
    }

    @Test
    void testMatchesEqual() {
        IntArray array = new IntArray(new int[]{3, 3, 3});
        ArrayContainer container = new ArrayContainer(1, "test", array);
        MaxValueSpecification spec = new MaxValueSpecification(3, ComparisonOperator.EQUAL);
        assertTrue(spec.matches(container));
    }
}