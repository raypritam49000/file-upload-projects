package com.jpa.api.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jpa.api.project.models.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,String> {

}
