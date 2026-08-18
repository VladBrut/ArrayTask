package com.innowise.arraytask.service.impl;

import com.innowise.arraytask.entity.AbstractArray;
import com.innowise.arraytask.service.ArrayStatistics;

import java.util.Optional;

public class ArrayStatisticsImpl implements ArrayStatistics {
    @Override
    public Optional<Double> min(AbstractArray array) {
        return findExtreme(array, false);
    }

    @Override
    public Optional<Double> max(AbstractArray array) {
        return findExtreme(array, true);
    }

    @Override
    public Optional<Double> sum(AbstractArray array) {
        double sum = 0.0;
        for (int i = 0; i < array.size(); i++) {
            sum += array.get(i).doubleValue();
        }
        return Optional.of(sum);
    }

    @Override
    public Optional<Double> average(AbstractArray array) {
        if (array.size() == 0) {
            return Optional.empty();
        }
        return sum(array).map(s -> s / array.size());
    }

    private Optional<Double> findExtreme(AbstractArray array, boolean findMax) {
        if (array.size() == 0) {
            return Optional.empty();
        }
        double extreme = array.get(0).doubleValue();
        for (int i = 1; i < array.size(); i++) {
            double current = array.get(i).doubleValue();
            if (findMax) {
                if (current > extreme) {
                    extreme = current;
                }
            } else {
                if (current < extreme) {
                    extreme = current;
                }
            }
        }
        return Optional.of(extreme);
    }
}
