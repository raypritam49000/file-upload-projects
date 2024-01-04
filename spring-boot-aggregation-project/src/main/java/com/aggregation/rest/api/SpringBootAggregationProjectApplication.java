package com.aggregation.rest.api;

import java.io.File;
import java.io.IOException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import com.aggregation.rest.api.dto.PersonDTO;
import com.aggregation.rest.api.utils.XmlToBeanMappingUtils;


@SpringBootApplication
public class SpringBootAggregationProjectApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAggregationProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try {
            File file = new ClassPathResource("Person.xml").getFile();
            PersonDTO person = XmlToBeanMappingUtils.mapXmlToObject(file, PersonDTO.class);
            System.out.println("@@@ Person ::: "+person);

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
