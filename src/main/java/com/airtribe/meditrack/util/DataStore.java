package com.airtribe.meditrack.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import com.airtribe.meditrack.entity.MedicalEntity;
import com.airtribe.meditrack.entity.Person;

public class DataStore<T extends MedicalEntity> {

    private final Map<Integer, T> store = new ConcurrentHashMap<>();

    // Save or update
    public void save(T entity) {
        Objects.requireNonNull(entity, "entity cannot be null");
        store.put(entity.getId(), entity);
    }

    // Find by ID
    public Optional<T> findById(int id) {
        return Optional.ofNullable(store.get(id));
    }

    // Get all
    public List<T> findAll() {
        return new ArrayList<>(store.values());
    }

    // Delete
    public void delete(int id) {
        store.remove(id);
    }

    // Exists check
    public boolean exists(int id) {
        return store.containsKey(id);
    }

    // Count
    public int count() {
        return store.size();
    }

    // Clear all (useful for testing/reset)
    public void clear() {
        store.clear();
    }
}
