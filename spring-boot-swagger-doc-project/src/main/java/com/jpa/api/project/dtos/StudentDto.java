package com.jpa.api.project.dtos;

public class StudentDto {
	
	private String id;
	
	private String fullName;
	
	private String email;
	
	private String city;
	
	public StudentDto() {
		super();
	}

	public StudentDto(String fullName, String email, String city) {
		super();
		this.fullName = fullName;
		this.email = email;
		this.city = city;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "StudentDto [id=" + id + ", fullName=" + fullName + ", email=" + email + ", city=" + city + "]";
	}

}
