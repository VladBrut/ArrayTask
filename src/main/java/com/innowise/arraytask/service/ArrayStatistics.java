package com.innowise.arraytask.service;

import com.innowise.arraytask.entity.AbstractArray;

import java.util.Optional;

public interface ArrayStatistics {
    Optional<Double> min(AbstractArray array);
    Optional<Double> max(AbstractArray array);
    Optional<Double> sum(AbstractArray array);
    Optional<Double> average(AbstractArray array);
}
