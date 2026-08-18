package com.innowise.arraytask.validator.impl;

import com.innowise.arraytask.validator.LineValidator;

import java.util.regex.Pattern;

public class ArrayLineValidator implements LineValidator {
    private static final Pattern NUMBER_PATTERN = Pattern.compile("^\\d+(\\.\\d+)?$");
    private static final Pattern DELIMITER_PATTERN = Pattern.compile("[\\s,;\\-]+");

    @Override
    public boolean isValid(String line) {
        if (line == null) {
            return false;
        }
        if (line.isBlank()) {
            return true;
        }
        String[] tokens = DELIMITER_PATTERN.split(line);
        for (String token : tokens) {
            if (token.isBlank()) {
                continue;
            }
            if (!NUMBER_PATTERN.matcher(token).matches()) {
                return false;
            }
        }
        return true;
    }
}
