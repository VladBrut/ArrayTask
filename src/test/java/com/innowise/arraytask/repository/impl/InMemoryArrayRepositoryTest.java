package com.innowise.arraytask.repository.impl;

import com.innowise.arraytask.comparator.ByIdComparator;
import com.innowise.arraytask.comparator.ByNameComparator;
import com.innowise.arraytask.entity.ArrayContainer;
import com.innowise.arraytask.entity.impl.IntArray;
import com.innowise.arraytask.specification.ComparisonOperator;
import com.innowise.arraytask.specification.Specification;
import com.innowise.arraytask.specification.impl.SumSpecification;
import com.innowise.arraytask.warehouse.Warehouse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InMemoryArrayRepositoryTest {

    private InMemoryArrayRepository repository;
    private Warehouse warehouse;

    @BeforeEach
    void setUp() {
        repository = InMemoryArrayRepository.getInstance();
        repository.clear();

        warehouse = Warehouse.getInstance();
        warehouse.clear();

        ArrayContainer c1 = new ArrayContainer(1, "First", new IntArray(new int[]{1, 2, 3}));
        ArrayContainer c2 = new ArrayContainer(2, "Second", new IntArray(new int[]{4, 5}));
        ArrayContainer c3 = new ArrayContainer(3, "Third", new IntArray(new int[]{10, 20, 30}));
        repository.add(c1);
        repository.add(c2);
        repository.add(c3);
    }

    @Test
    void testAddAndFindAll() {
        List<ArrayContainer> all = repository.findAll();
        assertEquals(3, all.size());
        assertTrue(all.stream().anyMatch(c -> c.id() == 1 && c.name().equals("First")));
        assertTrue(all.stream().anyMatch(c -> c.id() == 2 && c.name().equals("Second")));
        assertTrue(all.stream().anyMatch(c -> c.id() == 3 && c.name().equals("Third")));
    }

    @Test
    void testRemove() {
        ArrayContainer toRemove = repository.findById(2).getFirst();
        repository.remove(toRemove);
        List<ArrayContainer> all = repository.findAll();
        assertEquals(2, all.size());
        assertFalse(all.contains(toRemove));
        assertNull(warehouse.getStatistics(2));
    }

    @Test
    void testRemoveById() {
        repository.removeById(1);
        List<ArrayContainer> all = repository.findAll();
        assertEquals(2, all.size());
        assertFalse(all.stream().anyMatch(c -> c.id() == 1));
        assertNull(warehouse.getStatistics(1));
    }

    @Test
    void testFindById() {
        List<ArrayContainer> found = repository.findById(2);
        assertEquals(1, found.size());
        assertEquals("Second", found.getFirst().name());
    }

    @Test
    void testFindByName() {
        List<ArrayContainer> found = repository.findByName("Third");
        assertEquals(1, found.size());
        assertEquals(3, found.getFirst().id());
    }

    @Test
    void testFindBySpecification() {
        Specification sumGreaterThan10 = new SumSpecification(10, ComparisonOperator.GREATER);
        List<ArrayContainer> found = repository.findBySpecification(sumGreaterThan10);
        assertEquals(1, found.size());
        assertEquals("Third", found.getFirst().name());
    }

    @Test
    void testSortById() {
        repository.sort(new ByIdComparator());
        List<ArrayContainer> sorted = repository.findAll();
        assertEquals(1, sorted.get(0).id());
        assertEquals(2, sorted.get(1).id());
        assertEquals(3, sorted.get(2).id());
    }

    @Test
    void testSortByName() {
        repository.sort(new ByNameComparator());
        List<ArrayContainer> sorted = repository.findAll();
        assertEquals("First", sorted.get(0).name());
        assertEquals("Second", sorted.get(1).name());
        assertEquals("Third", sorted.get(2).name());
    }
}