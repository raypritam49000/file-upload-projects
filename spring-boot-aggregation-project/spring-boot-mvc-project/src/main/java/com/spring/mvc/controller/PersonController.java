package com.spring.mvc.controller;

import com.spring.mvc.dto.PersonDTO;
import com.spring.mvc.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class PersonController {

    @Autowired
    private PersonService personService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public String getPersonView() {
        return "index";
    }

    @RequestMapping(path = "/addPersonForm", method = RequestMethod.GET)
    public String getAddPersonView() {
        return "addPerson";
    }

    @ResponseBody
    @RequestMapping(path = "/submitPerson", method = RequestMethod.POST)
    public Map<Object,Object> handleAddPerson(PersonDTO personDTO) {
        System.out.println("ADD PERSON "+personDTO);
        return Map.of("statusCode",200,"status","success","message","Persons has been created","data",personService.createPerson(personDTO));
    }
}
