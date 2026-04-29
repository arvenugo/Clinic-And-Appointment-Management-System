package com.airtribe.meditrack.interfaces;

import java.util.List;

public interface Searchable<T> {

    /**
     * Generic search based on the keyword
     */
    List<T> search(String keyword);

    /**
     * Check if the keyword is a valid one or not
     */
    default boolean isValidKeyword(String keyword) {
        return keyword != null && !keyword.trim().isEmpty();
    }
}