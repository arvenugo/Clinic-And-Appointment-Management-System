package com.airtribe.meditrack.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DataStore<T> {

    private List<T> dataList;

    public DataStore() {
        this.dataList = new ArrayList<>();
    }

    // Add item
    public void add(T item) {
        dataList.add(item);
    }

    // Get all items
    public List<T> getAll() {
        return new ArrayList<>(dataList); // return copy for safety
    }

    // Get item by index
    public Optional<T> get(int index) {
        if (index >= 0 && index < dataList.size()) {
            return Optional.of(dataList.get(index));
        }
        return Optional.empty();
    }

    // Remove item
    public boolean remove(T item) {
        return dataList.remove(item);
    }

    // Update item at index
    public boolean update(int index, T newItem) {
        if (index >= 0 && index < dataList.size()) {
            dataList.set(index, newItem);
            return true;
        }
        return false;
    }

    // Size
    public int size() {
        return dataList.size();
    }
}
