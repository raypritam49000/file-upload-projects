package com.aggregation.rest.api.controllers;

import java.util.Date;
import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aggregation.rest.api.dto.EmployeeDTO;
import com.aggregation.rest.api.service.EmployeeService;
import com.aggregation.rest.api.utils.CsvGeneratorUtil;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/csv/export")
	public ResponseEntity<byte[]> generateCsvFile() {

		String fileName = "employees" + new Date().getTime() + ".csv";

		List<EmployeeDTO> employees = employeeService.getEmployees();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", fileName);

		String csvHeader = "Id,First Name,last Name,Email";
		
		List<Function<EmployeeDTO, Object>> fieldExtractors = List.of(EmployeeDTO::getId, EmployeeDTO::getFirstName,
				EmployeeDTO::getLastName, EmployeeDTO::getEmail);
		
		CsvGeneratorUtil<EmployeeDTO> csvGeneratorUtil = new CsvGeneratorUtil<>(fieldExtractors);
		
		byte[] csvBytes = csvGeneratorUtil.generateCsv(csvHeader, employees);

		return new ResponseEntity<>(csvBytes, headers, HttpStatus.OK);
	}

}
