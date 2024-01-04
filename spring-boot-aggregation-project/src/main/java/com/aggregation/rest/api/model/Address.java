package com.aggregation.rest.api.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "addresses")
public class Address {
    @Id
    private String id;
    private String city;
    private String state;
    private String country;
    private String pinCode;

    public Address(String city, String state, String country, String pinCode) {
        this.city = city;
        this.state = state;
        this.country = country;
        this.pinCode = pinCode;
    }
}
