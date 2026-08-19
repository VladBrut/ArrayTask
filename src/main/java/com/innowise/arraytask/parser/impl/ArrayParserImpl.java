package com.innowise.arraytask.parser.impl;

import com.innowise.arraytask.exception.ArrayProcessingException;
import com.innowise.arraytask.parser.ArrayParser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ArrayParserImpl implements ArrayParser {
    private static final Pattern DELIMITER_PATTERN = Pattern.compile("[\\s,;\\-]+");

    public List<Number> parse(String line) throws ArrayProcessingException {
        if (line == null) {
            throw new ArrayProcessingException("Input line cannot be null");
        }
        if (line.isBlank()) {
            return new ArrayList<>();
        }
        String[] tokens = DELIMITER_PATTERN.split(line);
        List<Number> numbers = new ArrayList<>();
        for (String token : tokens) {
            if (token.isBlank()) {
                continue;
            }
            try {
                if (token.contains(".")) {
                    numbers.add(Double.parseDouble(token));
                } else {
                    numbers.add(Integer.parseInt(token));
                }
            } catch (NumberFormatException e) {
                throw new ArrayProcessingException("Invalid number format: " + token, e);
            }
        }
        return numbers;
    }
}
