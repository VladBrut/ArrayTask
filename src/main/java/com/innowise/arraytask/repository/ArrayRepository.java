package com.innowise.arraytask.repository;

import com.innowise.arraytask.entity.ArrayContainer;
import com.innowise.arraytask.specification.Specification;

import java.util.Comparator;
import java.util.List;

public interface ArrayRepository {
    void add(ArrayContainer container);

    void remove(ArrayContainer container);

    void removeById(int id);

    List<ArrayContainer> findAll();

    List<ArrayContainer> findById(int id);

    List<ArrayContainer> findByName(String name);

    List<ArrayContainer> findBySpecification(Specification spec);

    void sort(Comparator<ArrayContainer> comparator);
}