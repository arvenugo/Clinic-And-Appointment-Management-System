package com.airtribe.meditrack.entity;

/**
 * Entity representing a person
 */
public class Person {

    /**
     * Name of the person
     */
    private final String name;

    /**
     * Age of the person
     */
    private int age;

    /**
     * Constr.
     *
     * @param name
     * @param age
     */
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Return name of the person
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Return age of the person
     *
     * @return int
     */
    public int getAge() {
        return age;
    }


}
