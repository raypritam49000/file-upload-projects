package com.jdbc.client.rest.api;

import com.jdbc.client.rest.api.model.Person;
import com.jdbc.client.rest.api.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootJdbcClientProjectApplication implements CommandLineRunner {

	@Autowired
	private PersonRepository personRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJdbcClientProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Person person = new Person(null,"Pritam Ray","ray@gmail.com","Ropar");
		personRepository.insertPerson(person);
	}
}
