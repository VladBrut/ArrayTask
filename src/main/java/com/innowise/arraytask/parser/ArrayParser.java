package com.innowise.arraytask.parser;

import com.innowise.arraytask.exception.ArrayProcessingException;
import java.util.List;

public interface ArrayParser {
    List<Number> parse(String line) throws ArrayProcessingException;
}