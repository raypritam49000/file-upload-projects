package com.aggregation.rest.api.service;

import com.aggregation.rest.api.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    public List<EmployeeDTO> getEmployee(String employeeId);
    public List<EmployeeDTO> getEmployees();
}
