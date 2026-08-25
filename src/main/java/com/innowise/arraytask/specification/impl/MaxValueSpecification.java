package com.innowise.arraytask.specification.impl;

import com.innowise.arraytask.entity.ArrayContainer;
import com.innowise.arraytask.specification.ComparisonOperator;
import com.innowise.arraytask.specification.Specification;

public class MaxValueSpecification implements Specification {
    private final double threshold;
    private final ComparisonOperator operator;

    public MaxValueSpecification(double threshold, ComparisonOperator operator) {
        this.threshold = threshold;
        this.operator = operator;
    }

    @Override
    public boolean matches(ArrayContainer container) {
        double max = container.array().max().orElse(Double.NaN);
        if (Double.isNaN(max)) {
            return false;
        }
        return operator.apply(max, threshold);
    }
}