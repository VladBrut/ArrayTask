package com.innowise.arraytask.warehouse;

import com.innowise.arraytask.entity.ArrayContainer;
import com.innowise.arraytask.service.ArrayStatistics;
import com.innowise.arraytask.service.impl.ArrayStatisticsImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Warehouse {
    private static final Logger logger = LogManager.getLogger(Warehouse.class);
    private static Warehouse instance;

    private final Map<Integer, Statistics> statsMap = new HashMap<>();
    private final ArrayStatistics statisticsService = new ArrayStatisticsImpl();

    private Warehouse() {
    }

    public static Warehouse getInstance() {
        if (instance == null) {
            instance = new Warehouse();
        }
        return instance;
    }

    public void updateStatistics(ArrayContainer container) {
        int id = container.id();
        Optional<Double> min = statisticsService.min(container.array());
        Optional<Double> max = statisticsService.max(container.array());
        Optional<Double> sum = statisticsService.sum(container.array());
        Optional<Double> avg = statisticsService.average(container.array());

        Statistics stats = new Statistics(
                min.orElse(Double.NaN),
                max.orElse(Double.NaN),
                sum.orElse(0.0),
                avg.orElse(Double.NaN)
        );
        statsMap.put(id, stats);
        logger.debug("Updated statistics for container {}: {}", id, stats);
    }

    public void removeStatistics(int id) {
        statsMap.remove(id);
        logger.debug("Removed statistics for container {}", id);
    }

    public Statistics getStatistics(int id) {
        return statsMap.get(id);
    }

    public Map<Integer, Statistics> getAllStatistics() {
        return new HashMap<>(statsMap);
    }

    public void clear() {
        statsMap.clear();
    }

    public record Statistics(double min, double max, double sum, double avg) {
        @Override
        public String toString() {
            return String.format("Statistics{min=%.2f, max=%.2f, sum=%.2f, avg=%.2f}", min, max, sum, avg);
        }
    }
}