package com.innowise.arraytask.parser;

import com.innowise.arraytask.exception.ArrayProcessingException;
import com.innowise.arraytask.parser.impl.ArrayParserImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArrayParserImplTest {
    private final ArrayParserImpl parser = new ArrayParserImpl();

    @Test
    void testParseValid() throws ArrayProcessingException {
        List<Number> numbers = parser.parse("1, 2, 3");
        assertEquals(3, numbers.size());
        assertEquals(1, numbers.get(0));
        assertEquals(2, numbers.get(1));
        assertEquals(3, numbers.get(2));
    }

    @Test
    void testParseEmpty() throws ArrayProcessingException {
        List<Number> numbers = parser.parse("");
        assertTrue(numbers.isEmpty());
    }

    @Test
    void testParseInvalidThrows() {
        assertThrows(ArrayProcessingException.class, () -> parser.parse("1, x, 3"));
    }
}