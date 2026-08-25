package com.innowise.arraytask.specification.impl;

import com.innowise.arraytask.entity.ArrayContainer;
import com.innowise.arraytask.entity.impl.IntArray;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NameSpecificationTest {

    @Test
    void testMatches() {
        ArrayContainer container = new ArrayContainer(1, "Hello", new IntArray(new int[]{1}));
        NameSpecification spec = new NameSpecification("Hello");
        assertTrue(spec.matches(container));
        spec = new NameSpecification("World");
        assertFalse(spec.matches(container));
    }
}