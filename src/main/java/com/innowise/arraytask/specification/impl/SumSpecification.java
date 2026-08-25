package com.innowise.arraytask.specification.impl;

import com.innowise.arraytask.entity.ArrayContainer;
import com.innowise.arraytask.specification.ComparisonOperator;
import com.innowise.arraytask.specification.Specification;

public class SumSpecification implements Specification {
    private final double threshold;
    private final ComparisonOperator operator;

    public SumSpecification(double threshold, ComparisonOperator operator) {
        this.threshold = threshold;
        this.operator = operator;
    }

    @Override
    public boolean matches(ArrayContainer container) {
        double sum = container.array().sum().orElse(0.0);
        return operator.apply(sum, threshold);
    }
}