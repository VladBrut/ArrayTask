package com.innowise.arraytask.reader.impl;

import com.innowise.arraytask.entity.AbstractArray;
import com.innowise.arraytask.exception.ArrayProcessingException;
import com.innowise.arraytask.factory.ArrayBuilder;
import com.innowise.arraytask.parser.ArrayParser;
import com.innowise.arraytask.reader.DataFileReader;
import com.innowise.arraytask.validator.LineValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DataFileReaderImpl implements DataFileReader {
    private static final Logger logger = LogManager.getLogger(DataFileReaderImpl.class);
    private final String filePath;
    private final LineValidator validator;
    private final ArrayParser parser;
    private final ArrayBuilder builder;

    public DataFileReaderImpl(String filePath, LineValidator validator, ArrayParser parser, ArrayBuilder builder) {
        this.filePath = filePath;
        this.validator = validator;
        this.parser = parser;
        this.builder = builder;
    }

    public List<AbstractArray> readArrays() throws ArrayProcessingException {
        List<AbstractArray> arrays = new ArrayList<>();
        Path path = Paths.get(filePath);
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(line -> {
                try {
                    if (validator.isValid(line)) {
                        List<Number> numbers = parser.parse(line);
                        builder.clear();
                        builder.addAllNumbers(numbers);
                        AbstractArray array = builder.build();
                        arrays.add(array);
                        logger.info("Created array of size {} from line: {}", array.size(), line);
                    } else {
                        logger.warn("Invalid line skipped: {}", line);
                    }
                } catch (ArrayProcessingException e) {
                    logger.error("Error processing line: {}", line, e);
                }
            });
        } catch (IOException e) {
            throw new ArrayProcessingException("Failed to read file: " + filePath, e);
        }
        return arrays;
    }
}