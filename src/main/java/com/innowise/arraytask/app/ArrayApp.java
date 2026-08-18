package com.innowise.arraytask.app;

import com.innowise.arraytask.entity.AbstractArray;
import com.innowise.arraytask.exception.ArrayProcessingException;
import com.innowise.arraytask.reader.DataFileReader;
import com.innowise.arraytask.service.ArrayStatistics;
import com.innowise.arraytask.service.ArraySorter;
import com.innowise.arraytask.service.impl.ArrayStatisticsImpl;
import com.innowise.arraytask.service.impl.ArraySorterImpl;
import com.innowise.arraytask.validator.impl.ArrayLineValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class ArrayApp {
    private static final Logger logger = LogManager.getLogger(ArrayApp.class);

    public static void main(String[] args) {
        String filePath = "data/input.txt";
        DataFileReader reader = new DataFileReader(filePath, new ArrayLineValidator());
        try {
            List<AbstractArray> arrays = reader.readArrays();
            ArrayStatistics statistics = new ArrayStatisticsImpl();
            ArraySorter sorter = new ArraySorterImpl();

            for (AbstractArray array : arrays) {
                logger.info("Array: {}", array);
                Optional<Double> min = statistics.min(array);
                Optional<Double> max = statistics.max(array);
                Optional<Double> sum = statistics.sum(array);
                Optional<Double> avg = statistics.average(array);
                logger.info("Min: {}, Max: {}, Sum: {}, Average: {}",
                        min.orElse(null), max.orElse(null), sum.orElse(null), avg.orElse(null));

                AbstractArray copy1 = array.copy();
                AbstractArray copy2 = array.copy();
                sorter.bubbleSort(copy1);
                sorter.quickSort(copy2);
                logger.info("Bubble sorted: {}", copy1);
                logger.info("Quick sorted: {}", copy2);
            }
        } catch (ArrayProcessingException e) {
            logger.error("Error: ", e);
        }
    }
}