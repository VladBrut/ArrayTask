package com.innowise.arraytask.repository;

import com.innowise.arraytask.entity.ArrayContainer;
import com.innowise.arraytask.exception.ArrayProcessingException;
import com.innowise.arraytask.specification.Specification;

import java.util.Comparator;
import java.util.List;

public interface ArrayRepository {
    void add(ArrayContainer container) throws ArrayProcessingException;

    void remove(ArrayContainer container) throws ArrayProcessingException;

    void removeById(int id) throws ArrayProcessingException;

    List<ArrayContainer> findAll();

    List<ArrayContainer> findById(int id) throws ArrayProcessingException;

    List<ArrayContainer> findByName(String name) throws ArrayProcessingException;

    List<ArrayContainer> findBySpecification(Specification spec) throws ArrayProcessingException;

    void sort(Comparator<ArrayContainer> comparator) throws ArrayProcessingException;
}