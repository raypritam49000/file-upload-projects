package com.aggregation.rest.api.dto;

import com.aggregation.rest.api.model.Address;
import lombok.Data;
import java.util.List;

@Data
public class EmployeeDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private List<Address> addresses;
}
