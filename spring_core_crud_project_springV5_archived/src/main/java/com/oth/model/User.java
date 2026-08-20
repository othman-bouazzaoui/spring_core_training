package com.oth.model;

public class User {
	private Long id;
	private String firstName;
	private String lastName;
	private Long age;

	public User(Long id, String firstName, String lastName, Long age) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}

	public User() {
	}

	public User(String firstName, String lastName, Long age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}
}
