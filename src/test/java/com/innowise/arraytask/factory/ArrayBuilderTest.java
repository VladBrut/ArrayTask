package com.innowise.arraytask.factory;

import com.innowise.arraytask.entity.AbstractArray;
import com.innowise.arraytask.entity.impl.DoubleArray;
import com.innowise.arraytask.entity.impl.IntArray;
import com.innowise.arraytask.exception.ArrayProcessingException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArrayBuilderTest {
    private final ArrayBuilder builder = new ArrayBuilder();

    @Test
    void testBuildIntArray() throws ArrayProcessingException {
        builder.clear().addAllNumbers(List.of(1, 2, 3));
        AbstractArray array = builder.build();
        assertInstanceOf(IntArray.class, array);
        assertEquals(3, array.size());
        assertEquals(1, array.get(0));
    }

    @Test
    void testBuildDoubleArray() throws ArrayProcessingException {
        builder.clear().addAllNumbers(List.of(1.5, 2.5));
        AbstractArray array = builder.build();
        assertInstanceOf(DoubleArray.class, array);
        assertEquals(2, array.size());
        assertEquals(1.5, array.get(0).doubleValue());
    }

    @Test
    void testBuildEmpty() throws ArrayProcessingException {
        builder.clear();
        AbstractArray array = builder.build();
        assertInstanceOf(DoubleArray.class, array);
        assertEquals(0, array.size());
    }
}