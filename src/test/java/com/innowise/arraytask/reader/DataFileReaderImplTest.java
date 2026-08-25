package com.innowise.arraytask.reader;

import com.innowise.arraytask.entity.AbstractArray;
import com.innowise.arraytask.factory.ArrayBuilder;
import com.innowise.arraytask.factory.impl.ArrayBuilderImpl;
import com.innowise.arraytask.parser.ArrayParser;
import com.innowise.arraytask.parser.impl.ArrayParserImpl;
import com.innowise.arraytask.reader.impl.DataFileReaderImpl;
import com.innowise.arraytask.validator.LineValidator;
import com.innowise.arraytask.validator.impl.ArrayLineValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DataFileReaderImplTest {

    @TempDir
    Path tempDir;

    @Test
    void testReadArrays() throws Exception {
        Path file = tempDir.resolve("input.txt");
        String content = "1, 2, 3\n4;5;6\n1, x, 3\n\n11- 2 - 42-\n";
        Files.write(file, content.getBytes());

        LineValidator validator = new ArrayLineValidator();
        ArrayParser parser = new ArrayParserImpl();
        ArrayBuilder builder = new ArrayBuilderImpl();
        DataFileReaderImpl reader = new DataFileReaderImpl(file.toString(), validator, parser, builder);

        List<AbstractArray> arrays = reader.readArrays();

        assertEquals(4, arrays.size());

        assertEquals(3, arrays.get(0).size());
        assertEquals(1, arrays.get(0).get(0));

        assertEquals(3, arrays.get(1).size());

        assertEquals(0, arrays.get(2).size());

        assertEquals(3, arrays.get(3).size());
        assertEquals(11, arrays.get(3).get(0));
    }
}