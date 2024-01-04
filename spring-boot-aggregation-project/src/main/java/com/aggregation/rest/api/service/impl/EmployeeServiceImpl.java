package com.aggregation.rest.api.service.impl;

import com.aggregation.rest.api.dto.EmployeeDTO;
import com.aggregation.rest.api.mappers.EmployeeMapper;
import com.aggregation.rest.api.model.Employee;
import com.aggregation.rest.api.repository.EmployeeRepository;
import com.aggregation.rest.api.service.EmployeeService;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;

	public final MongoTemplate mongoTemplate;

	public EmployeeServiceImpl(MongoTemplate mongoTemplate, EmployeeRepository employeeRepository) {
		this.mongoTemplate = mongoTemplate;
		this.employeeRepository = employeeRepository;
	}

	@Override
	public List<EmployeeDTO> getEmployee(String employeeId) {

		TypedAggregation<Employee> aggregation = TypedAggregation.newAggregation(Employee.class,
				Aggregation.match(Criteria.where("id").is(employeeId)));

		AggregationResults<Employee> results = mongoTemplate.aggregate(aggregation, Employee.class);

		return EmployeeMapper.INSTANCE.toDtoList(results.getMappedResults());
	}

	@Override
	public List<EmployeeDTO> getEmployees() {
		return EmployeeMapper.INSTANCE.toDtoList(employeeRepository.findAll());
	}

}
