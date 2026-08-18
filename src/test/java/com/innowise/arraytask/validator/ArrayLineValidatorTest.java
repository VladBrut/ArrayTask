package com.innowise.arraytask.validator;

import com.innowise.arraytask.validator.impl.ArrayLineValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArrayLineValidatorTest {
    private final LineValidator validator = new ArrayLineValidator();

    @Test
    void testValidLines() {
        assertTrue(validator.isValid("1, 2, 3"));
        assertTrue(validator.isValid("1;2;3"));
        assertTrue(validator.isValid("1 2 3"));
        assertTrue(validator.isValid("1 - 2 - 3"));
        assertTrue(validator.isValid(""));
        assertTrue(validator.isValid("   "));
        assertTrue(validator.isValid("1.5, 2.7"));
    }

    @Test
    void testInvalidLines() {
        assertFalse(validator.isValid("1, 2, x3"));
        assertFalse(validator.isValid("1, 2, 6..5"));
        assertFalse(validator.isValid("1y1 21 32"));
        assertFalse(validator.isValid("abc"));
    }
}