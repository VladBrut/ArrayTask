package com.innowise.arraytask.repository.impl;

import com.innowise.arraytask.entity.ArrayContainer;
import com.innowise.arraytask.observer.ArrayObservable;
import com.innowise.arraytask.observer.ArrayObserver;
import com.innowise.arraytask.repository.ArrayRepository;
import com.innowise.arraytask.specification.Specification;
import com.innowise.arraytask.warehouse.Warehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryArrayRepository implements ArrayRepository, ArrayObserver {
    private static final Logger logger = LogManager.getLogger(InMemoryArrayRepository.class);
    private static InMemoryArrayRepository instance;

    private final List<ArrayContainer> storage = new ArrayList<>();
    private final Warehouse warehouse = Warehouse.getInstance();

    private InMemoryArrayRepository() {}

    public static InMemoryArrayRepository getInstance() {
        if (instance == null) {
            instance = new InMemoryArrayRepository();
        }
        return instance;
    }

    @Override
    public void add(ArrayContainer container) {
        storage.add(container);
        container.array().addObserver(this);
        warehouse.updateStatistics(container);
        logger.info("Added container with id {}: {}", container.id(), container);
    }

    @Override
    public void remove(ArrayContainer container) {
        boolean removed = storage.remove(container);
        if (removed) {
            container.array().removeObserver(this);
            warehouse.removeStatistics(container.id());
            logger.info("Removed container: {}", container);
        } else {
            logger.warn("Container not found for removal: {}", container);
        }
    }

    @Override
    public void removeById(int id) {
        storage.stream()
                .filter(c -> c.id() == id)
                .findFirst()
                .ifPresent(this::remove);
    }

    @Override
    public List<ArrayContainer> findAll() {
        return new ArrayList<>(storage);
    }

    @Override
    public List<ArrayContainer> findById(int id) {
        return storage.stream()
                .filter(c -> c.id() == id)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArrayContainer> findByName(String name) {
        return storage.stream()
                .filter(c -> c.name().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public List<ArrayContainer> findBySpecification(Specification spec) {
        return storage.stream()
                .filter(spec::matches)
                .collect(Collectors.toList());
    }

    @Override
    public void sort(Comparator<ArrayContainer> comparator) {
        storage.sort(comparator);
        logger.info("Repository sorted with comparator: {}", comparator.getClass().getSimpleName());
    }

    @Override
    public void update(ArrayObservable observable, Object arg) {
        for (ArrayContainer container : storage) {
            if (container.array() == observable) {
                warehouse.updateStatistics(container);
                logger.info("Statistics updated for container id {} due to change", container.id());
                break;
            }
        }
    }

    // for tests
    public void clear() {
        storage.forEach(c -> c.array().removeObserver(this));
        storage.clear();
        warehouse.clear();
    }
}