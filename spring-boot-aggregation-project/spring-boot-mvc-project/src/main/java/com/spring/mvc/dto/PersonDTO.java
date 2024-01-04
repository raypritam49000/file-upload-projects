package com.spring.mvc.dto;

public class PersonDTO {
    private String id;
    private String name;
    private String email;
    private String city;
    private String contactNo;

    public PersonDTO() {
    }

    public PersonDTO(String id, String name, String email, String city, String contactNo) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.city = city;
        this.contactNo = contactNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", city='" + city + '\'' +
                ", contactNo='" + contactNo + '\'' +
                '}';
    }
}
