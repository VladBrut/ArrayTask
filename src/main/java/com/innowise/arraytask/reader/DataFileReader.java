package com.innowise.arraytask.reader;

import com.innowise.arraytask.entity.AbstractArray;
import com.innowise.arraytask.exception.ArrayProcessingException;
import java.util.List;

public interface DataFileReader {
    List<AbstractArray> readArrays() throws ArrayProcessingException;
}