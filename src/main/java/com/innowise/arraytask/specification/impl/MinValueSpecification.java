package com.innowise.arraytask.specification.impl;

import com.innowise.arraytask.entity.ArrayContainer;
import com.innowise.arraytask.specification.ComparisonOperator;
import com.innowise.arraytask.specification.Specification;

public class MinValueSpecification implements Specification {
    private final double threshold;
    private final ComparisonOperator operator;

    public MinValueSpecification(double threshold, ComparisonOperator operator) {
        this.threshold = threshold;
        this.operator = operator;
    }

    @Override
    public boolean matches(ArrayContainer container) {
        double min = container.array().min().orElse(Double.NaN);
        if (Double.isNaN(min)) {
            return false;
        }
        return operator.apply(min, threshold);
    }
}