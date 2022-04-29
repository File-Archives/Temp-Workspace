package com.rakuten;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Entity // Import entity from javax.persistence.
@Table(name = "users") // Since "User" is a reserved keyword in sql db, we are setting the table name to "users"
public class User {
	
	@Id // Import from javax.persistence
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id; //Best practice for id is using a wrapper class so that hibernate can handle all types of data.
	
	@NotBlank // Import from javax.*
	String name;
	
	@Min(value = 1, message = "Kindly, user age should be more than 1.")
	@Max(value = 150, message = "Bruh, Please...")
	int age; //for age, we can use @Positive as well, this @Min is for a minimum value. this can be applied to filters of the web page.

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	

}
