package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.enums.Gender;
import com.airtribe.meditrack.util.IDGenerator;

/**
 * Entity representing a person
 */
public class Person extends MedicalEntity{


    /**
     * Name of the person
     */
    private String name;

    /**
     * Age of the person
     */
    private int age;
    
    private Gender gender;
    

    /**
     * Constr.
     *
     * @param name
     * @param age
     */
    public Person(String name, int age,int id) {
    	super(id);
        this.name = name;
        this.age = age;
    }
    
  


	/**
	 * @param id
	 */
	public Person(Integer id) {
		super(id);
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


    /**
     * Set name
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set age
     *
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }



	/**
	 * @return the gender
	 */
	public Gender getGender() {
		return gender;
	}



	/**
	 * @param gender the gender to set
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}



}
