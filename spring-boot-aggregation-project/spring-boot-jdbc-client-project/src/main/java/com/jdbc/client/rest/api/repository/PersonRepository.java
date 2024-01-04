package com.jdbc.client.rest.api.repository;

import com.jdbc.client.rest.api.model.Person;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
public class PersonRepository {

    private final JdbcClient jdbcClient;

    public PersonRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public int insertPerson(Person person) {
        String sql = "INSERT INTO persons (name, email, city) VALUES (:name, :email, :city)";
        return jdbcClient.sql(sql)
                .param("name", person.name())
                .param("email", person.email())
                .param("city", person.city())
                .update();
    }




}
