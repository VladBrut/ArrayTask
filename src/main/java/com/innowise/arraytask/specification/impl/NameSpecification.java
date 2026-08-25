package com.innowise.arraytask.specification.impl;

import com.innowise.arraytask.entity.ArrayContainer;
import com.innowise.arraytask.specification.Specification;

public class NameSpecification implements Specification {
    private final String expectedName;

    public NameSpecification(String expectedName) {
        this.expectedName = expectedName;
    }

    @Override
    public boolean matches(ArrayContainer container) {
        return expectedName.equals(container.name());
    }
}