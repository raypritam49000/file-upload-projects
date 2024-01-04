package com.spring.mvc.services.impl;

import com.spring.mvc.dto.PersonDTO;
import com.spring.mvc.entity.Person;
import com.spring.mvc.repository.PersonRepository;
import com.spring.mvc.services.PersonService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public PersonDTO createPerson(PersonDTO personDTO) {
        Person person = new Person();
        BeanUtils.copyProperties(personDTO,person);
        Person createPerson = personRepository.save(person);
        PersonDTO createPersonDTO = new PersonDTO();
        BeanUtils.copyProperties(createPerson,createPersonDTO);
        return createPersonDTO;
    }
}
