package com.aggregation.rest.api.dto;

public class PersonDTO {
	private String name;
	private int age;
	private String city;
	private String email;
	
	
	public PersonDTO() {
		super();
	}

	public PersonDTO(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	

	public PersonDTO(String name, int age, String city, String email) {
		super();
		this.name = name;
		this.age = age;
		this.city = city;
		this.email = email;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

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

	@Override
	public String toString() {
		return "PersonDTO [name=" + name + ", age=" + age + ", city=" + city + ", email=" + email + "]";
	}


}
