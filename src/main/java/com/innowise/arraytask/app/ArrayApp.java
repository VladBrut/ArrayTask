package com.innowise.arraytask.app;

import com.innowise.arraytask.entity.AbstractArray;
import com.innowise.arraytask.entity.ArrayContainer;
import com.innowise.arraytask.exception.ArrayProcessingException;
import com.innowise.arraytask.factory.ArrayBuilder;
import com.innowise.arraytask.factory.impl.ArrayBuilderImpl;
import com.innowise.arraytask.parser.ArrayParser;
import com.innowise.arraytask.parser.impl.ArrayParserImpl;
import com.innowise.arraytask.reader.DataFileReader;
import com.innowise.arraytask.reader.impl.DataFileReaderImpl;
import com.innowise.arraytask.repository.impl.InMemoryArrayRepository;
import com.innowise.arraytask.specification.ComparisonOperator;
import com.innowise.arraytask.specification.Specification;
import com.innowise.arraytask.specification.impl.SumSpecification;
import com.innowise.arraytask.validator.LineValidator;
import com.innowise.arraytask.validator.impl.ArrayLineValidator;
import com.innowise.arraytask.warehouse.Warehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.List;

public class ArrayApp {
    private static final Logger logger = LogManager.getLogger(ArrayApp.class);

    public static void main(String[] args) {
        String filePath = "data/input.txt";

        LineValidator validator = new ArrayLineValidator();
        ArrayParser parser = new ArrayParserImpl();
        ArrayBuilder builder = new ArrayBuilderImpl();
        DataFileReader reader = new DataFileReaderImpl(filePath, validator, parser, builder);

        try {
            List<AbstractArray> arrays = reader.readArrays();

            InMemoryArrayRepository repository = InMemoryArrayRepository.getInstance();

            int idCounter = 1;
            for (AbstractArray array : arrays) {
                String name = "Array-" + idCounter;
                ArrayContainer container = new ArrayContainer(idCounter++, name, array);
                repository.add(container);
                logger.info("Added container: {}", container);
            }

            logger.info("All containers in repository:");
            repository.findAll().forEach(logger::info);

            Specification sumGreaterThan10 = new SumSpecification(10, ComparisonOperator.GREATER);
            List<ArrayContainer> found = repository.findBySpecification(sumGreaterThan10);
            logger.info("Containers with sum > 10: {}", found);

            repository.sort(Comparator.comparingInt(ArrayContainer::id));
            logger.info("Sorted by ID:");
            repository.findAll().forEach(logger::info);

            repository.removeById(1);
            logger.info("After removing container with ID 1:");
            repository.findAll().forEach(logger::info);

            if (!arrays.isEmpty()) {
                List<ArrayContainer> remaining = repository.findAll();
                if (!remaining.isEmpty()) {
                    ArrayContainer container = remaining.getFirst();
                    Warehouse warehouse = Warehouse.getInstance();
                    int id = container.id();

                    logger.info("Before change: array = {}, statistics = {}",
                            container.array(), warehouse.getStatistics(id));

                    container.array().set(0, 100);

                    logger.info("After change: array = {}, statistics = {}",
                            container.array(), warehouse.getStatistics(id));
                }
            }

        } catch (ArrayProcessingException e) {
            logger.error("Error processing arrays: ", e);
        }
    }
}