package com.innowise.arraytask.specification;

import com.innowise.arraytask.entity.ArrayContainer;

public interface Specification {
    boolean matches(ArrayContainer container);
}