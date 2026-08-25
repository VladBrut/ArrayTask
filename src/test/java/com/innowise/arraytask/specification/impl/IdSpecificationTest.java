package com.innowise.arraytask.specification.impl;

import com.innowise.arraytask.entity.ArrayContainer;
import com.innowise.arraytask.entity.impl.IntArray;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IdSpecificationTest {

    @Test
    void testMatches() {
        ArrayContainer container = new ArrayContainer(42, "test", new IntArray(new int[]{1}));
        IdSpecification spec = new IdSpecification(42);
        assertTrue(spec.matches(container));
        spec = new IdSpecification(99);
        assertFalse(spec.matches(container));
    }
}