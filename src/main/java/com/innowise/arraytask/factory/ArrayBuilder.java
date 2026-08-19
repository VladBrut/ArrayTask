package com.innowise.arraytask.factory;

import com.innowise.arraytask.entity.AbstractArray;
import com.innowise.arraytask.exception.ArrayProcessingException;
import java.util.List;

public interface ArrayBuilder {
    ArrayBuilder clear();
    ArrayBuilder addNumber(Number number);
    ArrayBuilder addAllNumbers(List<Number> numbers);
    AbstractArray build() throws ArrayProcessingException;
}