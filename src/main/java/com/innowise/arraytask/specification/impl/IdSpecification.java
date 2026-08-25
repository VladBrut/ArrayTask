package com.innowise.arraytask.specification.impl;

import com.innowise.arraytask.entity.ArrayContainer;
import com.innowise.arraytask.specification.Specification;

public class IdSpecification implements Specification {
    private final int expectedId;

    public IdSpecification(int expectedId) {
        this.expectedId = expectedId;
    }

    @Override
    public boolean matches(ArrayContainer container) {
        return container.id() == expectedId;
    }
}